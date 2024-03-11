package xyz.kbws.rpc.server;

/**
 * @author kbws
 * @date 2024/3/11
 * @description: HTTP 服务器接口
 */
public interface HttpServer {
    /**
     * 启动服务器
     * @param port
     */
    void doStart(int port);
}
