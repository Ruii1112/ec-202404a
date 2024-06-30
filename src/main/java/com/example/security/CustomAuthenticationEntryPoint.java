package com.example.security;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * カスタム認証エントリーポイントクラス.
 *
 * このクラスは、認証が必要なリソースに対して未認証のユーザーがアクセスした場合に呼び出されます。
 * 未認証のユーザーをカスタムエラーページにリダイレクトする役割を果たします。
 *
 * @author igamasayuki
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /**
     * 認証が必要なリソースに未認証のユーザーがアクセスした場合に呼び出されます。
     * 
     * @param request HttpServletRequestオブジェクト
     * @param response HttpServletResponseオブジェクト
     * @param authException 発生した認証例外
     * @throws IOException 入出力例外が発生した場合
     * @throws ServletException サーブレット例外が発生した場合
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // コンテキストパスを取得
        String contextPath = request.getContextPath();
        // エラーページへのリダイレクト
        response.sendRedirect(contextPath + "/error/4xx");
    }
}
