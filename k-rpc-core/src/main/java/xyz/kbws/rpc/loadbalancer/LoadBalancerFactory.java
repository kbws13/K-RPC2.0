package xyz.kbws.rpc.loadbalancer;

import xyz.kbws.rpc.spi.SpiLoader;

/**
 * @author kbws
 * @date 2024/3/14
 * @description: 负载均衡器工厂
 */
public class LoadBalancerFactory {
    static {
        SpiLoader.loadAll();
    }

    /**
     * 默认负载均衡器
     */
    private static final LoadBalancer DEFAULT_LOAD_BALANCER = new RoundRobinLoadBalancer();

    /**
     * 获取实例
     * @param key
     * @return
     */
    public static LoadBalancer getInstance(String key) {
        return SpiLoader.getInstance(LoadBalancer.class, key);
    }
}
