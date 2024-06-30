package com.example.domain;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * ログイン処理用のユーザ情報を保持するドメイン.
 *
 * @author YusakuTerashima
 */
public class LoginUser extends org.springframework.security.core.userdetails.User {

    private final User user;

    public LoginUser(User user, Collection<GrantedAuthority> authorities){
        super(user.getEmail(), user.getPassword(), authorities);
        this.user = user;
    }

    public User getUser(){
        return user;
    }
}
