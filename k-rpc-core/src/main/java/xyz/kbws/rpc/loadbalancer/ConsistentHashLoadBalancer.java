package xyz.kbws.rpc.loadbalancer;

import xyz.kbws.rpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author kbws
 * @date 2024/3/14
 * @description: 一致性哈希负载均衡器
 */
public class ConsistentHashLoadBalancer implements LoadBalancer{

    /**
     * 一致性 Hash 环，存放虚拟节点
     */
    private final TreeMap<Integer, ServiceMetaInfo> virtualNodes = new TreeMap<>();

    private static final int VIRTUAL_NODE_NUM = 100;

    @Override
    public ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList) {
        if (serviceMetaInfoList.isEmpty()) {
            return null;
        }

        // 构建虚拟头节点环
        for (ServiceMetaInfo serviceMetaInfo : serviceMetaInfoList) {
            for (int i = 0; i < VIRTUAL_NODE_NUM; i++) {
                int hash = getHash(serviceMetaInfo.getServiceAddress() + "#" + i);
                virtualNodes.put(hash, serviceMetaInfo);
            }
        }

        // 获取调用请求的 Hash 值
        int hash = getHash(requestParams);

        // 选择最接近且大于等于调用请求 Hash 值的虚拟节点
        Map.Entry<Integer, ServiceMetaInfo> entry = virtualNodes.ceilingEntry(hash);
        if (entry == null) {
            // 如果没有大于等于调用请求 Hash 的虚拟头节点，则返回首部的节点
            entry = virtualNodes.firstEntry();
        }
        return entry.getValue();
    }

    private int getHash(Object key) {
        return key.hashCode();
    }
}
