package com.example.controller;

import com.example.domain.User;
import com.example.form.UserRegisterForm;
import com.example.service.UserRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class UserRegisterController {

  @Autowired
  private UserRegisterService userRegisterService;

  /**
   * ユーザ登録画面へフォワードする.
   *
   * @param model リクエストスコープ
   * @param form ユーザ登録フォーム
   * @return ユーザ登録画面
   */
  @GetMapping("/toRegister")
  public String toRegister(Model model, UserRegisterForm form) {
    return "register";
  }

  /**
   * ユーザ登録情報を送信する.
   *
   * @param form ユーザ登録フォーム
   * @param result ユーザ登録画面
   * @param model リクエストスコープ
   * @return ユーザ登録画面へリダイレクト
   */
  @PostMapping("/register")
  public String register(@Validated UserRegisterForm form, BindingResult result, Model model){

    if(!form.getPassword().equals(form.getConfirmPassword())){
      result.rejectValue("confirmPassword", "", "確認用パスワードが一致しません");
    }

    if(userRegisterService.checkEmail(form.getEmail())){
      result.rejectValue("email", "duplicate", "メールアドレスが既に使用されています");
    }

    if(result.hasErrors()){
      return toRegister(model, form);
    }

    User user = new User();
    BeanUtils.copyProperties(form, user);
    user.setName(form.getLastName() + form.getFirstName());

    userRegisterService.insert(user);

    return "redirect:/login";
  }
}
