package com.example.service;

import com.example.domain.Bookmark;
import com.example.domain.LoginUser;
import com.example.domain.User;
import com.example.repository.BookmarkRepository;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * ブックマーク機能を処理するサービス.
 *
 * @author rui.inoue
 */
@Service
@Transactional
public class BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * ブックマーク情報の切り替え.
     *
     * @param loginUser ログインしているユーザ
     * @param itemId 商品のid
     */
    public void bookmark(LoginUser loginUser, Integer itemId){
        User user = loginUser.getUser();
        Bookmark bookmark = bookmarkRepository.findByUserIdAndItemId(user.getId(), itemId);
        if(bookmark == null){
            bookmark = new Bookmark();
            bookmark.setUserId(user.getId());
            bookmark.setItemId(itemId);
            bookmarkRepository.insert(bookmark);
        }else {
            bookmarkRepository.delete(bookmark.getId());
        }
        user.setId(null);
        User updateUser = userRepository.findByUniqueUserAttribute(user);

        updateUser(updateUser, loginUser);
    }

    /**
     * ログインユーザ情報の更新.
     *
     * @param updateUser 更新するユーザ情報
     * @param loginUser ログインしているユーザ
     */
    public void updateUser(User updateUser, LoginUser loginUser){
        Collection<GrantedAuthority> authorities = loginUser.getAuthorities();
        LoginUser updateLoginUser = new LoginUser(updateUser, authorities);

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(updateLoginUser, null, authorities);

        SecurityContextHolder.getContext().setAuthentication(token);
    }
}
