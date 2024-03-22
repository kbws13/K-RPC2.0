package xyz.kbws.rpc.fault.retry;

import lombok.extern.slf4j.Slf4j;
import xyz.kbws.rpc.model.RpcResponse;

import java.util.concurrent.Callable;

/**
 * @author kbws
 * @date 2024/3/22
 * @description: 不重试
 */
@Slf4j
public class NoRetryStrategy implements RetryStrategy{
    @Override
    public RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception {
        return callable.call();
    }
}
