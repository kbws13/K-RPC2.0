package xyz.kbws.rpc.fault.tolerant;

import xyz.kbws.rpc.model.RpcResponse;

import java.util.Map;

/**
 * @author kbws
 * @date 2024/3/22
 * @description: 降级到其他服务
 */
public class FailBackTolerantStrategy implements TolerantStrategy{
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        // todo 获取降级服务并调用
        return null;
    }
}
