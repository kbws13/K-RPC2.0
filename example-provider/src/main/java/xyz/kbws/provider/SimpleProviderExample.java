package xyz.kbws.provider;

import xyz.kbws.rpc.server.HttpServer;
import xyz.kbws.rpc.server.VertxHttpServer;

/**
 * @author kbws
 * @date 2024/3/11
 * @description: 简单服务提供者实例
 */
public class SimpleProviderExample {
    public static void main(String[] args) {
        // 提供服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
