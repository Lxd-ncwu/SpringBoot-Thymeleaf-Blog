package com.lxd.controller.admin;

import com.lxd.pojo.User;
import com.lxd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author : Lxd
 * @date : 18:50 2021/4/5
 * 用户登录的控制器
 */
@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserService userService;

    /**
     *  跳转登录页面
     * @return 登录页面
     */
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }


    /**
     * 登录校验
     * @param username 用户名
     * @param password 密码
     * @param session session域
     * @param attributes 校验错误返回页面信息
     * @return 登录成功跳转主页 失败跳转登录页面
     */
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username,password);
        if (user!=null && user.getId()==1){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else{
            attributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/admin";
        }
    }


    /**
     * 注销
     * @param session session域 注销要删除其中的内容
     * @return 返回登录页面
     */
    @GetMapping("/logout")
    public String login(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }

}
