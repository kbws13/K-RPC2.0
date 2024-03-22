package xyz.kbws.rpc.fault.tolerant;

import xyz.kbws.rpc.model.RpcResponse;

import java.util.Map;

/**
 * @author kbws
 * @date 2024/3/22
 * @description: 容错策略
 */
public interface TolerantStrategy {

    /**
     * 容错
     * @param context 上下文，用于传递数据
     * @param e 异常
     * @return
     */
    RpcResponse doTolerant(Map<String, Object> context, Exception e);
}
