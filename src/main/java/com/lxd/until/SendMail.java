package com.lxd.until;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author : Lxd
 * @date : 17:58 2021/5/15
 */

@Component
public class SendMail {
    @Autowired
    private JavaMailSenderImpl mailSender;
    public void send(String check_code , String mail) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("lxd_jwj@163.com");
        mimeMessageHelper.setSubject("Blog激活码");
        mimeMessageHelper.setText("欢迎注册LxdのBlod,激活码为<span style='color:red'>"+check_code+"</span>,此激活码5分钟内有效，请及时激活",true);
        mimeMessageHelper.setTo(mail);
        mailSender.send(mimeMessage);
    }
}
