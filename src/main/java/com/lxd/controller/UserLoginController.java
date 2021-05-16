package com.lxd.controller;

import com.lxd.pojo.User;
import com.lxd.service.UserService;
import com.lxd.until.SendMail;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author : Lxd
 * @date : 18:43 2021/4/15
 */
@Controller
public class UserLoginController {
    @Autowired
    private UserService userService;

    @Autowired
    RedisTemplate<String,Object> redisTemplate ;

    @Autowired
    SendMail sendMail;

    //跳转注册
    @GetMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

    @PostMapping("/register")
    //注册
    public String register(RedirectAttributes attributes , String barcode) throws MessagingException {
        if (redisTemplate.opsForValue().get(barcode) == null) {
            attributes.addFlashAttribute("message","验证码有误");
            return "redirect:toRegister";
        }else{
            User user_redis = (User) redisTemplate.opsForValue().get(barcode);
            userService.saveUser(user_redis);
            attributes.addFlashAttribute("message","注册成功");
            return "redirect:toLogin";
        }
    }


    @PostMapping("/redis_register")
    public void redis_register(User user ,  RedirectAttributes attributes) throws MessagingException {
        User user1 = userService.findByUsername(user.getUsername());
        User user2 = userService.findByNickname(user.getNickname());
        User user3 = userService.findByEmail(user.getEmail());
        String check_code = UUID.randomUUID().toString().substring(0, 5);
        if (user1 != null) {
            attributes.addFlashAttribute("message", "用户名已经存在");
            return;
        }
        if (user2 != null) {
            attributes.addFlashAttribute("message", "昵称已经存在");
            return;
        }
        if (user3 != null) {
            attributes.addFlashAttribute("message", "该邮箱已经被注册！");
            return;
        }
        sendMail.send(check_code, user.getEmail());
        redisTemplate.opsForValue().set(check_code, user, 5, TimeUnit.MINUTES);
    }


    @GetMapping("/toLogin")
    //跳转登录页面
    public String toLogin(){
        return "login";
    }

    @PostMapping("/login")
    //登录
    public String login(HttpSession session , @RequestParam String username, @RequestParam String password ,  RedirectAttributes attributes){
        User checkUser = userService.checkUser(username, password);
        if (checkUser != null) {
            checkUser.setPassword(null);
            session.setAttribute("user",checkUser);
            return "redirect:/";
        }  else {
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/toLogin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/";
    }
}
