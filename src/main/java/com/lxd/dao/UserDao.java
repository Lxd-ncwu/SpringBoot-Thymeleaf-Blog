package com.lxd.dao;

import com.lxd.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author : Lxd
 * @date : 18:34 2021/4/5
 */
@Mapper
@Repository
public interface UserDao {
    //查询用户名和密码
    //@Param 可以将参数传递给SQL 不用在xml文件中加ParamType
    User findByUserNameAndPassword(@Param("username")String username , @Param("password") String Password);

    //增加用户
    int saveUser(User user);

    //根据username查询
    User findByUsername(String username);

    //根据nickname查询
    User findByNickname(String nickname);

    User findByEmail(String email);
}
