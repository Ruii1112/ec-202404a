package com.example.service;

import com.example.domain.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ユーザ情報を登録するサービス.
 *
 * @author YusakuTerashima
 */
@Service
@Transactional
public class UserRegisterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * パスワードをハッシュ化して登録する.
     *
     * @param user 登録するユーザ情報
     */
    public void insert(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.insert(user);
    }

    /**
     * ユーザ自身を除いてメールアドレスが重複しているユーザがいないか検索.
     *
     * @param email 重複チェックを行いたいユーザのメールアドレス
     * @return 存在しない場合false,する場合true
     */
    public boolean checkEmail(String email){
        return userService.getUserByEmail(email) != null;
    }
}
