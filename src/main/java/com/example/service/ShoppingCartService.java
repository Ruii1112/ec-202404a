package com.example.service;

import com.example.common.Status;
import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.domain.OrderTopping;
import com.example.form.AddItemForm;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import com.example.repository.OrderToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * ショッピングカートの処理のサービス.
 *
 * @author rui.inoue
 */
@Service
@Transactional
public class ShoppingCartService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderToppingRepository orderToppingRepository;

    /**
     * 商品の追加.
     *
     * @param userId ユーザid
     * @param form 入力情報
     * @return 注文情報
     */
    public Order addItem(Integer userId, AddItemForm form){
        Order order = orderRepository.findByUserIdAndStatus(userId, Status.BEFORE_ORDER.getKey());
        if(order == null){
            order = new Order();
            order.setUserId(userId);
            order.setStatus(Status.BEFORE_ORDER.getKey());
            order.setTotalPrice(0);
            order.setOrderTime(LocalDateTime.now());
            order = orderRepository.insert(order);
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setItemId(form.getItemId());
        orderItem.setOrderId(order.getId());
        orderItem.setQuantity(form.getQuantity());
        orderItem.setSize(form.getSize().toCharArray()[0]);
        orderItem = orderItemRepository.insert(orderItem);

        if(form.getToppingList() != null) {
            for (Integer toppingId : form.getToppingList()) {
                OrderTopping orderTopping = new OrderTopping();
                orderTopping.setOrderItemId(orderItem.getId());
                orderTopping.setToppingId(toppingId);
                orderToppingRepository.insert(orderTopping);
            }
        }

        return order;
    }

    /**
     * 注文情報の取得.
     *
     * @param userId ユーザid
     * @return 注文情報
     */
    public Order showOrder(Integer userId){
        return orderRepository.findAllOrderInfoByUserIdAndStatus(userId, Status.BEFORE_ORDER.getKey());
    }

    /**
     * カート内の商品削除.
     *
     * @param orderItemId 商品id
     */
    public void deleteItem(Integer orderItemId){
        orderItemRepository.deleteById(orderItemId);
    }
}
