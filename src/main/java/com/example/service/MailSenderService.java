package com.example.service;

import com.example.domain.Order;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

/**
 * メール送信を処理するサービス.
 *
 * @author rui.inoue
 */
@Service
public class MailSenderService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String fromAddress;

    /**
     * メール送信.
     *
     * @param toAddress 送信先アドレス
     * @param paymentMethod 支払い方法
     * @param order 注文情報
     * @throws MessagingException メール関連の例外処理
     */
    @Async
    public void mailSender(String toAddress, String paymentMethod, Order order) throws MessagingException {
        Map<String, Object> variables = new HashMap<>();
        variables.put("order", order);
        variables.put("paymentMethod", paymentMethod);

        MimeMessage  message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

        messageHelper.setFrom(fromAddress);
        messageHelper.setTo(toAddress);
        messageHelper.setSubject("注文完了のお知らせ");

        Context context = new Context();
        context.setVariables(variables);

        String htmlContent = templateEngine.process("mail/order-finished", context);

        messageHelper.setText(htmlContent, true);

        javaMailSender.send(message);
    }
}
