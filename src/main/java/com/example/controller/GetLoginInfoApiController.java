package com.example.controller;

import com.example.domain.LoginUser;
import com.example.domain.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * ログイン者情報をレスポンスするapiコントローラ.
 *
 * @author rui.inoue
 */
@RestController
@RequestMapping("/get-user")
public class GetLoginInfoApiController {

    @Autowired
    private UserService userService;

    /**
     * ユーザ情報を渡す.
     *
     * @param loginUser ログインしているユーザ
     * @return ユーザ情報
     */
    @GetMapping("/user-info")
    public Map<String, User> getLoginInfo(@AuthenticationPrincipal LoginUser loginUser){
        User user = loginUser.getUser();
        Map<String, User> map = new HashMap<>();
        map.put("user", user);
        return map;
    }
}
