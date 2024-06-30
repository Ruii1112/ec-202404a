package com.example.controller;

import com.example.domain.Item;
import com.example.domain.LoginUser;
import com.example.domain.User;
import com.example.form.ReviewForm;
import com.example.service.ShowItemDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品の詳細表示機能を操作するコントローラ.
 *
 * @author rui.inoue
 */
@Controller
@RequestMapping("/show-item-detail")
public class ShowItemDetailController {

  @Autowired
  private ShowItemDetailService showItemDetailService;

  /**
   * 商品の詳細画面の表示.
   *
   * @param id 商品のid
   * @param model 商品の格納
   * @param loginUser ログインしているユーザ
   * @param form レビューフォーム
   * @return 商品詳細画面
   */
  @GetMapping()
  public String toItemDetail(Integer id, Model model, @AuthenticationPrincipal LoginUser loginUser, ReviewForm form) {
    Item item = showItemDetailService.showItem(id);
    model.addAttribute("item", item);

    boolean bookmarkFlag = isBookMark(loginUser, item);

    model.addAttribute("bookmarkFlag", bookmarkFlag);
    model.addAttribute("itemId", id);
    return "item-detail";
  }

  /**
   * ログインユーザが商品をブックマークしているかの判定.
   *
   * @param loginUser ログインユーザ
   * @param item 商品
   * @return ブックマークしているかの真理値
   */
  public boolean isBookMark(LoginUser loginUser, Item item){
    if(loginUser == null){
      return false;
    }

    User user = loginUser.getUser();
    if(user.getBookmarkList() == null){
      return false;
    }else {
      for (Item userItem: user.getBookmarkList()){
        if(userItem.getId().equals(item.getId())){
          return true;
        }
      }
      return false;
    }
  }
}
