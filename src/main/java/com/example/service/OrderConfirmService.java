package com.example.service;

import com.example.domain.Order;
import com.example.domain.OrderItem;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 注文確認処理のサービス.
 *
 * @author rui.inoue
 */
@Service
@Transactional
public class OrderConfirmService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    /**
     * 主キーから注文情報の取得.
     *
     * @param orderId 注文情報のid
     * @return 注文情報
     */
    public Order showConfirmOrder(Integer orderId){
        return orderRepository.findById(orderId);
    }

    /**
     * 注文情報のIDの更新.
     *
     * @param order 注文情報
     */
    public void updateUserId(Order order){
        orderRepository.update(order);
    }

    /**
     * 商品情報のIDの更新.
     *
     * @param orderItem 注文情報にある商品
     */
    public void updateOrderItem(OrderItem orderItem){
        orderItemRepository.updateOrderItem(orderItem);
    }

    /**
     * IDに基づいたオーダーを削除.
     *
     * @param id 削除するオーダーID
     */
    public void deleteById(Integer id){
        orderRepository.deleteById(id);
    }
}
