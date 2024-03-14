package xyz.kbws.rpc.loadbalancer;

import xyz.kbws.rpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;

/**
 * @author kbws
 * @date 2024/3/14
 * @description: 负载均衡器（消费端使用）
 */
public interface LoadBalancer {

    /**
     * 选择业务调用
     * @param requestParams
     * @param serviceMetaInfoList
     * @return
     */
    ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList);
}
