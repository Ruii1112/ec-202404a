package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ログイン前に不正なURLでアクセスされた際に4xxページに遷移する.
 * 
 * @author igamasayuki 
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

    /**
     * 4xxページに遷移する.
     *
     * @return 4xxページ
     */
    @GetMapping("/4xx")
    public String handle4xx() {
        // resources/templates/error/4xx.html にフォワード
        return "error/4xx";
    }
}
