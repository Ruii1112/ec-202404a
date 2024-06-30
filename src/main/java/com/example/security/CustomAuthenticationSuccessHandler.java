package com.example.security;

import com.example.controller.ShoppingCartController;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.User;
import com.example.service.OrderConfirmService;
import com.example.service.ShoppingCartService;
import com.example.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 認証後の処理用のクラス.
 *
 * @author rui.inoue
 */
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final RequestCache requestCache = new HttpSessionRequestCache();

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShoppingCartController shoppingCartController;

    @Autowired
    private OrderConfirmService orderConfirmService;

    @Autowired
    private HttpSession session;

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException{

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String userName = userDetails.getUsername();
        User user = userService.getUserByEmail(userName);

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

        SavedRequest savedRequest = requestCache.getRequest(request, response);
        String contextPath = request.getContextPath();

        if(savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            response.sendRedirect(targetUrl);
        }else {
            response.sendRedirect(contextPath + "/");
        }
    }
}
