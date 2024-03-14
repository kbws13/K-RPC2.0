package xyz.kbws.provider;

import xyz.kbws.common.service.UserService;
import xyz.kbws.rpc.RpcApplication;
import xyz.kbws.rpc.config.RegistryConfig;
import xyz.kbws.rpc.config.RpcConfig;
import xyz.kbws.rpc.model.ServiceMetaInfo;
import xyz.kbws.rpc.registry.LocalRegistry;
import xyz.kbws.rpc.registry.Registry;
import xyz.kbws.rpc.registry.RegistryFactory;
import xyz.kbws.rpc.server.HttpServer;
import xyz.kbws.rpc.server.VertxHttpServer;
import xyz.kbws.rpc.server.tcp.VertxTcpServer;

/**
 * @author kbws
 * @date 2024/3/13
 * @description: 服务提供者示例
 */
public class ProviderExample {
    public static void main(String[] args) {
        // RPC 框架初始化
        RpcApplication.init();

        // 注册服务
        String serviceName = UserService.class.getName();
        LocalRegistry.register(serviceName, UserServiceImpl.class);

        // 注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 启动 web 服务
        //HttpServer httpServer = new VertxHttpServer();
        //httpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
        // 启动 TCP 服务
        VertxTcpServer vertxTcpServer = new VertxTcpServer();
        vertxTcpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
