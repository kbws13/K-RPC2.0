package xyz.kbws.consumer;

import xyz.kbws.common.model.User;
import xyz.kbws.common.service.UserService;
import xyz.kbws.rpc.proxy.ServiceProxyFactory;

/**
 * @author kbws
 * @date 2024/3/11
 * @description: 简单服务消费者示例
 */
public class SimpleConsumerExample {
    public static void main(String[] args) {
        // 静态代理
        //UserService userService = new UserServiceProxy();
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("kbws");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        }else {
            System.out.println("user==null");
        }
    }
}
