package xyz.kbws.rpc.fault.tolerant;

import lombok.extern.slf4j.Slf4j;
import xyz.kbws.rpc.model.RpcResponse;

import java.util.Map;

/**
 * @author kbws
 * @date 2024/3/22
 * @description: 静默处理异常
 */
@Slf4j
public class FailSafeTolerantStrategy implements TolerantStrategy{
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        log.info("静默处理异常", e);
        return new RpcResponse();
    }
}
