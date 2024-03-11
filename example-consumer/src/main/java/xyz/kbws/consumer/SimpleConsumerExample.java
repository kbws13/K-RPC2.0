package xyz.kbws.consumer;

import xyz.kbws.common.model.User;
import xyz.kbws.common.service.UserService;

/**
 * @author kbws
 * @date 2024/3/11
 * @description: 简单服务消费者示例
 */
public class SimpleConsumerExample {
    public static void main(String[] args) {
        // todo 需要获取 UserService 的实现类对象
        UserService userService = null;
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
