package com.example.controller;

import com.example.domain.LoginUser;
import com.example.domain.User;
import com.example.form.UserMyPageUpdateForm;
import com.example.service.UserDetailsService;
import com.example.service.UserMyPageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

/**
 * ユーザマイページでユーザ情報の操作を行うコントローラ.
 *
 * @author krkrHotaru
 */
@Controller
@RequestMapping("")
public class UserMyPageController {

  @Autowired
  private UserMyPageService userMyPageService;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private OrderHistoryController orderHistoryController;

  /**
   * ユーザマイページ画面を表示します.
   *
   * @param model Requestスコープの準備
   * @param form ユーザ情報更新のためのフォームの準備
   * @param loginUser ログイン情報
   * @return ユーザマイページ画面
   */
  @GetMapping("/to-my-page")
  public String toMyPage(Model model, UserMyPageUpdateForm form, @AuthenticationPrincipal LoginUser loginUser) {
    User user = loginUser.getUser();
    BeanUtils.copyProperties(user, form);
    if (!user.getBookmarkList().isEmpty()){
      model.addAttribute("bookMarkList",user.getBookmarkList());
    }
    model.addAttribute("recommends",userMyPageService.getItemListNotBookMark(user.getBookmarkList()).subList(0,3));
    orderHistoryController.orderHistory(model,loginUser);
    return "my-page";
  }

  /**
   * ユーザ情報の編集を行います.
   *
   * @param form ユーザの入力フォーム情報
   * @param result バリデーションチェック結果
   * @param loginUser ログイン情報
   * @param model Requestスコープの準備
   * @return ユーザマイページ画面
   */
  @PostMapping("/my-page")
  public String myPage(@Validated UserMyPageUpdateForm form, BindingResult result, @AuthenticationPrincipal LoginUser loginUser , Model model){
    User fixedUserInfo = new User();
    BeanUtils.copyProperties(form, fixedUserInfo);
    fixedUserInfo.setId(loginUser.getUser().getId());
    if (userMyPageService.isExistDuplicateEmailExceptUser(fixedUserInfo)){
      result.rejectValue("email", "duplicate", "メールアドレスが既に使用されています");
    }
    if(result.hasErrors()){
      return toMyPage(model, form, loginUser);
    }
    userMyPageService.updateUserInfo(fixedUserInfo);

    return "redirect:/to-my-page";
  }

  /**
   * ユーザ情報の論理削除を行い、ログアウトします.
   *
   * @param loginUser ログイン情報
   * @return ログイン画面へ遷移
   */
  @GetMapping("/delete-account")
  public String deleteAccount(@AuthenticationPrincipal LoginUser loginUser){
    User user = loginUser.getUser();
    user.setDeletedAt(LocalDateTime.now());
    userMyPageService.updateUserInfo(user);
    return "redirect:/logout";
  }
}
