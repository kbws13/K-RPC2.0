package xyz.kbws.rpc.proxy;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.index.qual.SameLen;
import xyz.kbws.rpc.RpcApplication;
import xyz.kbws.rpc.config.RpcConfig;
import xyz.kbws.rpc.constants.RpcConstants;
import xyz.kbws.rpc.fault.retry.RetryStrategy;
import xyz.kbws.rpc.fault.retry.RetryStrategyFactory;
import xyz.kbws.rpc.fault.tolerant.TolerantStrategy;
import xyz.kbws.rpc.fault.tolerant.TolerantStrategyFactory;
import xyz.kbws.rpc.loadbalancer.LoadBalancer;
import xyz.kbws.rpc.loadbalancer.LoadBalancerFactory;
import xyz.kbws.rpc.model.RpcRequest;
import xyz.kbws.rpc.model.RpcResponse;
import xyz.kbws.rpc.model.ServiceMetaInfo;
import xyz.kbws.rpc.protocol.*;
import xyz.kbws.rpc.registry.Registry;
import xyz.kbws.rpc.registry.RegistryFactory;
import xyz.kbws.rpc.serializer.JdkSerializer;
import xyz.kbws.rpc.serializer.Serializer;
import xyz.kbws.rpc.serializer.SerializerFactory;
import xyz.kbws.rpc.server.tcp.VertxTcpClient;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author kbws
 * @date 2024/3/11
 * @description: 服务代理（JDK 动态代理）
 */
@Slf4j
public class ServiceProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 发请求
        String serviceName = method.getDeclaringClass().getName();
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(serviceName)
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        try {
            // 从注册中心获取服务提供者请求地址
            RpcConfig rpcConfig = RpcApplication.getRpcConfig();
            Registry registry = RegistryFactory.getInstance(rpcConfig.getRegistryConfig().getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceVersion(RpcConstants.DEFAULT_SERVICE_VERSION);
            List<ServiceMetaInfo> serviceMetaInfoList = registry.serviceDiscovery(serviceMetaInfo.getServiceKey());
            if (CollUtil.isEmpty(serviceMetaInfoList)) {
                throw new RuntimeException("暂无服务地址");
            }
            // 负载均衡
            LoadBalancer loadBalancer = LoadBalancerFactory.getInstance(rpcConfig.getLoadBalancer());
            // 将调用方法名（请求路径）作为负载均衡参数
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put("methodName", rpcRequest.getMethodName());
            ServiceMetaInfo selectedServiceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);
            // 发送请求
            //try (HttpResponse httpResponse = HttpRequest.post(selectedServiceMetaInfo.getServiceHost() + ":" + selectedServiceMetaInfo.getServicePort())
            //        .body(bodyBytes)
            //        .execute()) {
            //    byte[] result = httpResponse.bodyBytes();
            //    // 反序列化
            //    RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
            //    return rpcResponse.getData();
            //}
            // 发送 TCP 请求
            // 使用重试机制
            RpcResponse response;
            try {
                RetryStrategy retryStrategy = RetryStrategyFactory.getInstance(rpcConfig.getRetryStrategy());
                response = retryStrategy.doRetry(() ->
                        VertxTcpClient.doRequest(rpcRequest, selectedServiceMetaInfo)
                );
            } catch (Exception e) {
                // 容错机制
                TolerantStrategy tolerantStrategy = TolerantStrategyFactory.getInstance(rpcConfig.getTolerantStrategy());
                response = tolerantStrategy.doTolerant(null, e);
            }
            return response.getData();
        } catch (Exception e) {
            log.info("ServiceProxy中抛出了异常:{}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
