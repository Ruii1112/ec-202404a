package com.example.service;

import com.example.domain.Bookmark;
import com.example.domain.Item;
import com.example.domain.User;
import com.example.repository.ItemRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * ユーザ情報をマイページに持ってくるサービス.
 *
 * @author krkrHotaru
 */

@Service
@Transactional
public class UserMyPageService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private ItemRepository itemRepository;

    /**
     * 受け取ったユーザ情報でテーブルとセッション双方の登録情報を上書きします.
     *
     * @param user ユーザ情報
     */
    public void updateUserInfo(User user){
        userRepository.update(user);
        updateUserDetails(user.getEmail());
    }

    /**
     * 受け取ったユーザ以外にemailの重複がないか確認します.
     *
     * @param user 重複確認をしたいユーザの情報
     */
    public boolean isExistDuplicateEmailExceptUser(User user){
        return userRepository.findByUniqueUserAttribute(user) != null;
    }

    /**
     * ユーザ情報変更時の再認証処理を行います.
     *
     * @param newEmail 新しいメールアドレス
     */
    public void updateUserDetails(String newEmail) {
        Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();

        UserDetails newUserDetails = userDetailsService.loadUserByUsername(newEmail);
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(
                newUserDetails, currentAuth.getCredentials(), newUserDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }

    public List<Item> getItemListNotBookMark(List<Item> bookmarkItemList){
        List<Integer> notInValue = new ArrayList<>();
        for (Item item : bookmarkItemList){
            notInValue.add(item.getId());
        }
        if (notInValue.isEmpty()){
            notInValue.add(0);
        }

        return itemRepository.findExceptIdValues(notInValue);
    }
}
