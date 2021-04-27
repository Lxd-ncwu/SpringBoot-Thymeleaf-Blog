package com.lxd.controller;

import com.lxd.pojo.User;
import com.lxd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author : Lxd
 * @date : 18:43 2021/4/15
 */
@Controller
public class UserLoginController {
    @Autowired
    private UserService userService;


    //跳转注册
    @GetMapping("/toRegister")
    public String toRegister(){
        return "register";
    }

    @PostMapping("/register")
    //注册
    public String register(User user ,  RedirectAttributes attributes){
        User user1 = userService.findByUsername(user.getUsername());
        User user2 = userService.findByNickname(user.getNickname());
        if (user1 != null) {
            attributes.addFlashAttribute("message","用户名已经存在");
            return "redirect:toRegister";
        }
        if (user2 != null){
            attributes.addFlashAttribute("message","昵称已经存在");
            return "redirect:toRegister";
        }
        userService.saveUser(user);
        attributes.addFlashAttribute("message","注册成功");
        return "redirect:toLogin";
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
