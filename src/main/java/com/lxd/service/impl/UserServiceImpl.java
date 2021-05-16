package com.lxd.service.impl;

import com.lxd.dao.UserDao;
import com.lxd.pojo.User;
import com.lxd.service.UserService;
import com.lxd.until.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author : Lxd
 * @date : 18:46 2021/4/5
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        return userDao.findByUserNameAndPassword(username, MD5Utils.code(password));
    }

    @Override
    public int saveUser(User user) {
        user.setCreateTime(new Date());
        user.setPassword(MD5Utils.code(user.getPassword()));
        return userDao.saveUser(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findByNickname(String nickname) {
        return userDao.findByNickname(nickname);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

}
