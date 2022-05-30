package cn.bianwenkai.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author BianWenKai
 * @DATE 2022/5/30 - 22:53
 **/

@Component
public class MailService {

    @Resource
    private JavaMailSender javaMailSender;

    public void sendMail(String from, String to, String cc, String subject, String text) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(from);  // 发送者
        mail.setTo(to);  //收件人
        mail.setCc(cc);  //抄送人
        mail.setSubject(subject); //邮件主题
        mail.setText(text);   //邮件内容
        javaMailSender.send(mail);  //发送邮件
    }
}
