package xyz.kbws.rpc;

import lombok.extern.slf4j.Slf4j;
import xyz.kbws.rpc.config.RegistryConfig;
import xyz.kbws.rpc.config.RpcConfig;
import xyz.kbws.rpc.constants.RpcConstants;
import xyz.kbws.rpc.registry.Registry;
import xyz.kbws.rpc.registry.RegistryFactory;
import xyz.kbws.rpc.utils.ConfigUtils;

/**
 * @author kbws
 * @date 2024/3/11
 * @description: RPC 框架应用，相当于 holder，存放了项目全局用到的变量，双检锁单例模式实现
 */
@Slf4j
public class RpcApplication {

    private static volatile RpcConfig rpcConfig;

    /**
     * 框架初始化
     */
    public static void init(RpcConfig newRpcConfig) {
        rpcConfig = newRpcConfig;
        log.info("rpc init, config={}",newRpcConfig.toString());
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        registry.init(registryConfig);
        log.info("registry init, config={}", registryConfig);

        // 创建并注册 Shutdown Hook，JVM 退出时执行操作
        Runtime.getRuntime().addShutdownHook(new Thread(registry::destroy));
    }

    /**
     * 初始化
     */
    public static void init() {
        RpcConfig newRpcConfig;
        try {
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstants.DEFAULT_config_prefix);
        } catch (Exception e) {
            // 加载配置失败，使用默认值
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig);
    }

    /**
     * 获取配置
     */
    public static RpcConfig getRpcConfig() {
        if (rpcConfig == null) {
            synchronized (RpcApplication.class) {
                if (rpcConfig == null) {
                    init();
                }
            }
        }
        return rpcConfig;
    }
}
