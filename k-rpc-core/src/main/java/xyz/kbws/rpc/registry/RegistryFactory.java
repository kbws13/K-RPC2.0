package xyz.kbws.rpc.registry;

import xyz.kbws.rpc.spi.SpiLoader;

/**
 * @author kbws
 * @date 2024/3/13
 * @description: 注册中心工厂
 */
public class RegistryFactory {
    static {
        SpiLoader.loadAll();
    }

    /**
     * 默认注册中心
     */
    private static final Registry DEFAULT_REGISTRY = new EtcdRegistry();

    public static Registry getInstance(String key) {
        return SpiLoader.getInstance(Registry.class, key);
    }
}
