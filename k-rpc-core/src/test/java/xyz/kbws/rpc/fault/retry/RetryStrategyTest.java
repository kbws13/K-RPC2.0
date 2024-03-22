package xyz.kbws.rpc.fault.retry;

import org.junit.Test;
import xyz.kbws.rpc.model.RpcResponse;

/**
 * @author kbws
 * @date 2024/3/22
 * @description: 重试策略测试
 */
public class RetryStrategyTest {
    RetryStrategy retryStrategy = new NoRetryStrategy();

    @Test
    public void doRetry() {
        try {
            RpcResponse response = retryStrategy.doRetry(() -> {
                System.out.println("测试重试");
                throw new RuntimeException("模拟重试失败");
            });
            System.out.println(response);
        } catch (Exception e) {
            System.out.println("重试多次失败");
            e.printStackTrace();
        }
    }
}
