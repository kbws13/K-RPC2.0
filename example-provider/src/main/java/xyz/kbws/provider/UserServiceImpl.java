package xyz.kbws.provider;

import xyz.kbws.common.model.User;
import xyz.kbws.common.service.UserService;

/**
 * @author kbws
 * @date 2024/3/11
 * @description: 用户服务实现类
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println("用户名:" + user.getName());
        return user;
    }
}
