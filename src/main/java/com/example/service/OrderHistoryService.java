package com.example.service;

import com.example.domain.Order;
import com.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 注文履歴の処理のサービス.
 *
 * @author rui.inoue
 */
@Service
@Transactional
public class OrderHistoryService {

    @Autowired
    private OrderRepository orderRepository;

    /**
     * ユーザidによる注文情報の取得.
     *
     * @param userId ユーザid
     * @param statuses 注文状況の配列
     * @return 注文情報のリスト
     */
    public List<Order> showOrderHistory(Integer userId, Integer[] statuses){
        return orderRepository.findByUserIdAndStatuses(userId, statuses);
    }
}
