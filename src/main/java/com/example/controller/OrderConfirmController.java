package com.example.controller;

import com.example.common.PaymentMethod;
import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.domain.User;
import com.example.form.OrderForm;
import com.example.service.ShoppingCartService;
import com.example.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 注文確認処理を制御するコントローラ.
 *
 * @author rui.inoue
 */
@Controller
@RequestMapping("/order-confirm")
public class OrderConfirmController {

  @Autowired
  private ShoppingCartService shoppingCartService;

  @Autowired
  private UserDetailsService userDetailsService;

  /**
   * 注文確認画面の表示.
   *
   * @param model 注文情報の格納
   * @param loginUser ログインしているユーザ
   * @param orderForm 注文情報の入力
   * @return 注文確認画面
   */
  @GetMapping("")
  public String showConfirmOrder(Model model, @AuthenticationPrincipal LoginUser loginUser, OrderForm orderForm) {
    User user = loginUser.getUser();
    Integer loginUserId = user.getId();

    Order order = shoppingCartService.showOrder(loginUserId);
    model.addAttribute("order", order);

    if(orderForm.getPaymentMethod() == null) {
      orderForm.setPaymentMethod(PaymentMethod.MONEY.getKey());
    }
    return "order-confirm";
  }
}
