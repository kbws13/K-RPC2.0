package xyz.kbws.rpc.config;

import lombok.Data;

/**
 * @author kbws
 * @date 2024/3/13
 * @description: RPC 框架注册中心配置类
 */
@Data
public class RegistryConfig {
    /**
     * 注册中心类别
     */
    private String registry = "etcd";

    /**
     * 注册中心地址
     */
    private String address = "http://127.0.0.1:2380";

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 超时时间（单位毫秒）
     */
    private Long timeout = 10000L;
}
