package xyz.kbws.consumer;

import xyz.kbws.rpc.config.RpcConfig;
import xyz.kbws.rpc.utils.ConfigUtils;

/**
 * @author kbws
 * @date 2024/3/11
 * @description:
 */
public class ConsumerExample {
    public static void main(String[] args) {
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class,"rpc");
        System.out.println(rpc);
    }
}
