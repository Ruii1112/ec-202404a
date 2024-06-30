package com.example.service;

import com.example.domain.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ユーザ関連の機能を処理するサービス.
 *
 * @author rui.inoue
 */
@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 主キーによるユーザ情報の取得.
     *
     * @param userId ユーザの主キー
     * @return ユーザ情報
     */
    public User getUser(Integer userId){
        User user = new User();
        user.setId(userId);
        return userRepository.findByUniqueUserAttribute(user);
    }

    /**
     * メールアドレスによるユーザ情報の取得.
     *
     * @param email メールアドレス
     * @return ユーザ情報
     */
    public User getUserByEmail(String email){
        User user = new User();
        user.setEmail(email);
        return userRepository.findByUniqueUserAttribute(user);
    }
}
