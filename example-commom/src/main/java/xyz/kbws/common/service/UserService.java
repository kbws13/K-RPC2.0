package xyz.kbws.common.service;

import xyz.kbws.common.model.User;

/**
 * @author kbws
 * @date 2024/3/10
 * @description: 用户服务
 */
public interface UserService {
    /**
     * 获取用户
     * @param user
     * @return
     */
    User getUser(User user);
}
