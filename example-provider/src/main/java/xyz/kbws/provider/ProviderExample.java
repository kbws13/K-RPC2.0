package xyz.kbws.provider;

import xyz.kbws.common.service.UserService;
import xyz.kbws.rpc.RpcApplication;
import xyz.kbws.rpc.bootstrap.ProviderBootstrap;
import xyz.kbws.rpc.config.RegistryConfig;
import xyz.kbws.rpc.config.RpcConfig;
import xyz.kbws.rpc.model.ServiceMetaInfo;
import xyz.kbws.rpc.model.ServiceRegisterInfo;
import xyz.kbws.rpc.registry.LocalRegistry;
import xyz.kbws.rpc.registry.Registry;
import xyz.kbws.rpc.registry.RegistryFactory;
import xyz.kbws.rpc.server.HttpServer;
import xyz.kbws.rpc.server.VertxHttpServer;
import xyz.kbws.rpc.server.tcp.VertxTcpServer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kbws
 * @date 2024/3/13
 * @description: 服务提供者示例
 */
public class ProviderExample {
    public static void main(String[] args) {
        // 要注册的服务
        List<ServiceRegisterInfo<?>> serviceRegisterInfoList = new ArrayList<>();
        ServiceRegisterInfo serviceRegisterInfo = new ServiceRegisterInfo(UserService.class.getName(), UserServiceImpl.class);
        serviceRegisterInfoList.add(serviceRegisterInfo);

        // 服务提供者初始化
        ProviderBootstrap.init(serviceRegisterInfoList);
    }
}
