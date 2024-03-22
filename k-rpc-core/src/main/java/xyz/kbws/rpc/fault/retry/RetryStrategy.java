package xyz.kbws.rpc.fault.retry;

import xyz.kbws.rpc.model.RpcResponse;

import java.util.concurrent.Callable;

/**
 * @author kbws
 * @date 2024/3/22
 * @description: 重试策略
 */
public interface RetryStrategy {

    /**
     * 重试
     * @param callable
     * @return
     * @throws Exception
     */
    RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception;
}
