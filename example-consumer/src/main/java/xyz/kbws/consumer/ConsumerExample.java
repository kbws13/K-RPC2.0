package xyz.kbws.consumer;

import xyz.kbws.common.model.User;
import xyz.kbws.common.service.UserService;
import xyz.kbws.rpc.bootstrap.ConsumerBootstrap;
import xyz.kbws.rpc.config.RpcConfig;
import xyz.kbws.rpc.proxy.ServiceProxyFactory;
import xyz.kbws.rpc.utils.ConfigUtils;

/**
 * @author kbws
 * @date 2024/3/11
 * @description:
 */
public class ConsumerExample {
    public static void main(String[] args) {
        // 服务提供者初始化
        ConsumerBootstrap.init();

        // 获取代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("kbws");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user==null");
        }
    }
}
