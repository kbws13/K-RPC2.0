package xyz.kbws.consumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import io.vertx.core.http.HttpServerResponse;
import xyz.kbws.common.model.User;
import xyz.kbws.common.service.UserService;
import xyz.kbws.rpc.model.RpcRequest;
import xyz.kbws.rpc.model.RpcResponse;
import xyz.kbws.rpc.serializer.JdkSerializer;
import xyz.kbws.rpc.serializer.Serializer;

/**
 * @author kbws
 * @date 2024/3/11
 * @description: 静态代理
 */
public class UserServiceProxy implements UserService {
    @Override
    public User getUser(User user) {
        // 指定序列化器
        Serializer serializer = new JdkSerializer();
        // 发请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(UserService.class.getName())
                .methodName("getUser")
                .parameterTypes(new Class[]{User.class})
                .args(new Object[]{user})
                .build();
        try {
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            byte[] result;
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                    .body(bodyBytes)
                    .execute()){
                result = httpResponse.bodyBytes();
            }
            RpcResponse rpcResponse = serializer.deserialize(result, RpcResponse.class);
            return  (User) rpcResponse.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
