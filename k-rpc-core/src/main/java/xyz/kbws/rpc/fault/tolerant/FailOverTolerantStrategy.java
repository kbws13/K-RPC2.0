package xyz.kbws.rpc.fault.tolerant;

import lombok.extern.slf4j.Slf4j;
import xyz.kbws.rpc.model.RpcResponse;

import java.util.Map;

/**
 * @author kbws
 * @date 2024/3/22
 * @description: 转移到其他服务节点
 */
@Slf4j
public class FailOverTolerantStrategy implements TolerantStrategy{
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        // todo 获取其他服务节点，然后调用
        return null;
    }
}
