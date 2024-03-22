package xyz.kbws.rpc.fault.tolerant;

import xyz.kbws.rpc.model.RpcResponse;

import java.util.Map;

/**
 * @author kbws
 * @date 2024/3/22
 * @description: 快速失败（立即通知外层调用方）
 */
public class FailFastTolerantStrategy implements TolerantStrategy{
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        throw new RuntimeException("服务报错",e);
    }
}
