package xyz.kbws.rpc.server;

import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import xyz.kbws.rpc.RpcApplication;
import xyz.kbws.rpc.model.RpcRequest;
import xyz.kbws.rpc.model.RpcResponse;
import xyz.kbws.rpc.registry.LocalRegistry;
import xyz.kbws.rpc.serializer.JdkSerializer;
import xyz.kbws.rpc.serializer.Serializer;
import xyz.kbws.rpc.serializer.SerializerFactory;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author kbws
 * @date 2024/3/11
 * @description: HTTP 请求处理
 */
public class HttpServerHandler implements Handler<HttpServerRequest> {
    @Override
    public void handle(HttpServerRequest request) {
        // 指定序列化器
        final Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());
        // 记录日志
        System.out.println("Received request: " + request.method() + " " + request.uri());
        // 异步处理 HTTP 请求
        request.bodyHandler(body -> {
            byte[] bytes = body.getBytes();
            RpcRequest rpcRequest = null;
            try {
                rpcRequest = serializer.deserialize(bytes, RpcRequest.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 构造响应结果对象
            RpcResponse rpcResponse = new RpcResponse();
            // 如果请求为 null，直接返回
            if (rpcRequest == null) {
                rpcResponse.setMessage("rpcRequest is null!");
                doResponse(request, rpcResponse, serializer);
                return;
            }
            try {
                // 获取要调用的服务实现类，通过反射调用
                Class<?> implClass = LocalRegistry.get(rpcRequest.getServiceName());
                Method method = implClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
                Object result = method.invoke(implClass.newInstance(), rpcRequest.getArgs());
                // 封装返回结果
                rpcResponse.setData(result);
                rpcResponse.setDataType(method.getReturnType());
                rpcResponse.setMessage("ok");
            } catch (Exception e) {
                e.printStackTrace();
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setException(e);
            }
            // 响应
            doResponse(request, rpcResponse, serializer);
        });
    }

    /**
     * 响应
     * @param request
     * @param response
     * @param serializer
     */
    void doResponse(HttpServerRequest request, RpcResponse response, Serializer serializer) {
        HttpServerResponse httpServerResponse = request.response()
                .putHeader("content-type","application/json");
        try {
            // 序列化
            byte[] serialized = serializer.serialize(response);
            httpServerResponse.end(Buffer.buffer(serialized));
        } catch (IOException e) {
            e.printStackTrace();
            httpServerResponse.end(Buffer.buffer());
        }
    }
}
