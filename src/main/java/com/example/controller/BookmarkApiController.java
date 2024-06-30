package com.example.controller;

import com.example.domain.LoginUser;
import com.example.domain.User;
import com.example.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * ブックマーク機能の処理を制御するコントローラ.
 *
 * @author rui.inoue
 */
@RestController
@RequestMapping("/bookmark")
public class BookmarkApiController {

    @Autowired
    private BookmarkService bookmarkService;

    /**
     * ブックマーク.
     *
     * @param itemId 商品のid
     * @param loginUser ログインしているユーザ
     * @return 成功かどうか
     */
    @PostMapping()
    public Map<String, String> bookmark(Integer itemId, @AuthenticationPrincipal LoginUser loginUser){
        User user = loginUser.getUser();
        Map<String, String> map = new HashMap<>();
        bookmarkService.bookmark(loginUser, itemId);
        map.put("status", "success");
        return map;
    }
}
