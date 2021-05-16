package com.lxd.service;

import com.lxd.pojo.User;

/**
 * @author : Lxd
 * @date : 18:45 2021/4/5
 */
public interface UserService {
    /**
     * 检查用户
     * @param username 用户名
     * @param password 密码
     * @return User
     */
    User checkUser(String username , String password);

    int saveUser(User user);

    User findByUsername(String username);

    User findByNickname(String nickname);

    User findByEmail(String email);
}
