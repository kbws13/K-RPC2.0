package xyz.kbws.rpc.proxy;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import xyz.kbws.rpc.model.RpcRequest;
import xyz.kbws.rpc.model.RpcResponse;
import xyz.kbws.rpc.serializer.JdkSerializer;
import xyz.kbws.rpc.serializer.Serializer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author kbws
 * @date 2024/3/11
 * @description: 服务代理（JDK 动态代理）
 */
public class ServiceProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 指定序列化器
        Serializer serializer = new JdkSerializer();
        // 发请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        try {
            // 序列化
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            // 发送请求
            // todo 地址被硬编码了（需要使用注册中心和服务发现机制解决）
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                    .body(bodyBytes)
                    .execute()) {
                byte[] result = httpResponse.bodyBytes();
                // 反序列化
                RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
                return rpcResponse.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
