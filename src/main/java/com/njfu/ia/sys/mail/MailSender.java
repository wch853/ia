package com.njfu.ia.sys.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MailSender {

    @Resource
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    private static final Logger LOGGER = LoggerFactory.getLogger(MailSender.class);

    /**
     * batch send simple mail
     *
     * @param subject title
     * @param text    text
     * @param toList  toList
     */
    public void sendSimpleMail(String subject, String text, List<String> toList) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        int succeedSendCount = 0;
        for (String to : toList) {
            try {
                mailMessage.setTo(to);
                mailSender.send(mailMessage);
                succeedSendCount++;
            } catch (MailException e) {
                LOGGER.error("send to {} exception, {}", to, e.getMessage());
            }
        }
        LOGGER.info("send {} mails, {} succeed", toList.size(), succeedSendCount);
    }
}
