package xyz.kbws.provider;

import xyz.kbws.common.service.UserService;
import xyz.kbws.rpc.registry.LocalRegistry;
import xyz.kbws.rpc.server.HttpServer;
import xyz.kbws.rpc.server.VertxHttpServer;

/**
 * @author kbws
 * @date 2024/3/11
 * @description: 简单服务提供者实例
 */
public class SimpleProviderExample {
    public static void main(String[] args) {
        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);
        // 提供服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
