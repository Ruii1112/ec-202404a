package com.example.service;

import com.example.controller.ShoppingCartController;
import com.example.domain.LoginUser;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

/**
 * ログイン処理を行うサービス.
 * 認証を行うために，Spring SecurityのUserDetailsServiceインターフェースを実装している。
 *
 * @author YusakuTerashima
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartController shoppingCartController;

    @Autowired
    private OrderConfirmService orderConfirmService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private HttpSession session;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Not found mail address:" + email);
        }

        Order loginUserOrder = shoppingCartService.showOrder(user.getId());
        Integer tmpId = shoppingCartController.extractNumbers(session.getId());

        Order tmpOrder = shoppingCartService.showOrder(tmpId);
        if (tmpOrder != null) {
            if (loginUserOrder == null) {
                tmpOrder.setUserId(user.getId());
                orderConfirmService.updateUserId(tmpOrder);
            } else {
                for (OrderItem orderItem : tmpOrder.getOrderItemList()) {
                    orderItem.setOrderId(loginUserOrder.getId());
                    orderConfirmService.updateOrderItem(orderItem);
                }
                orderConfirmService.deleteById(tmpOrder.getId());
            }
        }

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new LoginUser(user, authorities);
    }

}
