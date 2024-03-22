package xyz.kbws.rpc.fault.tolerant;

import xyz.kbws.rpc.spi.SpiLoader;

/**
 * @author kbws
 * @date 2024/3/22
 * @description: 容错策略工厂
 */
public class TolerantStrategyFactory {
    static {
        SpiLoader.loadAll();
    }

    /**
     * 默认容错策略
     */
    private static final TolerantStrategy DEFAULT_TOLERANT_STRATEGY = new FailFastTolerantStrategy();

    /**
     * 获取实例
     * @param key
     * @return
     */
    public static TolerantStrategy getInstance(String key) {
        return SpiLoader.getInstance(TolerantStrategy.class, key);
    }
}
