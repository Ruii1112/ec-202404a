package com.example.controller;

import com.example.common.PaymentMethod;
import com.example.domain.CreditCard;
import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.domain.User;
import com.example.form.OrderForm;
import com.example.service.MailSenderService;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import jakarta.mail.MessagingException;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注文処理の制御のコントローラ.
 *
 * @author rui.inoue
 */
@Controller
@RequestMapping("/order")
@EnableAsync
public class OrderController {

    @Autowired
    private OrderConfirmController orderConfirmController;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MailSenderService mailSenderService;

    /**
     * 注文.
     *
     * @param orderForm 入力情報
     * @param result    バリデーションチェック
     * @param model     注文画面に渡すmodel
     * @param loginUser ログインしているユーザ
     * @return 注文完了画面
     */
    @PostMapping("")
    public String order(@Validated OrderForm orderForm, BindingResult result, Model model, @AuthenticationPrincipal LoginUser loginUser) {
        User user = loginUser.getUser();
        Integer paymentMethodKey = 0;
        for (PaymentMethod paymentMethod : PaymentMethod.values()) {
            if ("クレジットカード".equals(paymentMethod.getValue())) {
                paymentMethodKey = paymentMethod.getKey();
            }
        }

        if (paymentMethodKey.equals(orderForm.getPaymentMethod())) {
            CreditCard creditCard = new CreditCard();
            creditCard.setUser_id(user.getId());
            creditCard.setOrder_number("12345678912345");
            creditCard.setCard_number(orderForm.getCardNumber());
            creditCard.setCard_exp_year(orderForm.getCardExpYear());
            creditCard.setCard_exp_month(orderForm.getCardExpMonth());
            creditCard.setCard_name(orderForm.getCardName());
            creditCard.setCard_cvv(orderForm.getCardCvv());

            boolean checkCreditCard = orderService.checkCreditCard(creditCard, orderForm.getOrderId());

            if (orderForm.getPaymentMethod().equals(paymentMethodKey) && !checkCreditCard) {
                result.rejectValue("cardNumber", "", "カードが使えません");
            }
        }

        if(result.hasErrors()){
            return orderConfirmController.showConfirmOrder(model, loginUser, orderForm);
        }

        orderService.order(orderForm);

        Order order = orderService.order(orderForm);

        try {
            mailSenderService.mailSender(orderForm.getDestinationEmail(), PaymentMethod.of(orderForm.getPaymentMethod()).getValue(), order);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return "redirect:/order/order-complete";
    }

    /**
     * 注文完了画面の表示.
     *
     * @return 注文完了画面
     */
    @GetMapping("/order-complete")
    public String orderComplete(){
        return "order-complete";
    }
}
