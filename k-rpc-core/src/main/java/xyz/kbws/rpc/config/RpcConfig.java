package xyz.kbws.rpc.config;

import lombok.Data;

/**
 * @author kbws
 * @date 2024/3/11
 * @description: RPC 框架配置类
 */
@Data
public class RpcConfig {
    /**
     * 名称
     */
    private String name = "k-rpc";

    /**
     * 版本号
     */
    private String version = "1.0";

    /**
     * 服务器主机名
     */
    private String serverHost = "localhost";

    /**
     * 服务器端口号
     */
    private Integer serverPort = 8080;
}
