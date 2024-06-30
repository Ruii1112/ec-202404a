package com.example.controller;

import com.example.domain.LoginUser;
import com.example.domain.Review;
import com.example.domain.User;
import com.example.form.ReviewForm;
import com.example.service.ReviewService;
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

/**
 * レビュー機能の処理の制御用コントローラ.
 *
 * @author rui.inoue
 */
@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ShowItemDetailController showItemDetailController;
    
    /**
     * レビューの保存.
     *
     * @param form レビューの入力情報
     * @param result バリデーションチェック
     * @param model レビュー投稿画面に渡す
     * @param loginUser ログインしているユーザ
     * @return 商品詳細ページへリダイレクト
     */
    @PostMapping("/add")
    public String add(@Validated ReviewForm form, BindingResult result, Model model, @AuthenticationPrincipal LoginUser loginUser){
        User user = loginUser.getUser();
        if(result.hasErrors()){
            model.addAttribute("isOpenReviewForm", true);
            return showItemDetailController.toItemDetail(form.getItemId(),model, loginUser, form);
        }

        Review review = new Review();
        BeanUtils.copyProperties(form, review);
        reviewService.addReview(review, user.getId());
        return "redirect:/show-item-detail?id=" + form.getItemId();
    }
}
