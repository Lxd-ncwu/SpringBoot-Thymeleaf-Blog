package com.lxd;

import com.lxd.dao.UserDao;
import com.lxd.pojo.User;
import com.lxd.until.SendMail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.mail.MessagingException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class BlogLxdApplicationTests {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Autowired
    SendMail sendMail;

    @Autowired
    UserDao userDao;

    User user = new User();
    @Test
    void contextLoads() throws MessagingException {


    }

}
