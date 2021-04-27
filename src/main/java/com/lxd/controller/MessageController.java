package com.lxd.controller;

import com.lxd.pojo.Message;
import com.lxd.pojo.User;
import com.lxd.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author : Lxd
 * @date : 21:17 2021/4/14
 */
@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Value("${message.avatar}")
    private String avatar;

    //查询留言
    @GetMapping("/messages")
    public String messages(Model model) {
        List<Message> messages = messageService.listMessage();
        model.addAttribute("messages", messages);
        return "message";
    }

    //新增留言
    @PostMapping("/messages")
    public String post(Model model, Message message, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user.getId() == 1) {
            message.setAvatar(user.getAvatar());
            message.setAdminMessage(true);
        } else {
            message.setAvatar(avatar);
        }
        if (message.getParentMessage().getId() != null) {
            message.setParentMessageId(message.getParentMessage().getId());
            int i = messageService.saveMessage(message);
            List<Message> messages = messageService.listMessage();
            model.addAttribute("messages", messages);
        }
        return "message::messageList";
    }

    //删除留言
    @GetMapping("/messages/{id}/delete")
    public String delete(@PathVariable Long id, Model model) {
        messageService.deleteMessage(id);
        List<Message> messages = messageService.listMessage();
        model.addAttribute("messages", messages);
        return "redirect:/messages";
    }
}
