package com.example.service;

import com.example.domain.Order;
import com.example.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * {@link OrderHistoryService} のテスト.
 */
@ExtendWith(MockitoExtension.class)
class OrderHistoryServiceTest {

    @InjectMocks
    OrderHistoryService orderHistoryService;

    @Mock
    OrderRepository orderRepository;

    /**
     * {@link OrderHistoryService#showOrderHistory(Integer, Integer[])} のテスト
     */
    @DisplayName("注文のリストの取得")
    @Test
    void showOrderHistory() {
        List<Order> orderList = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Order order = new Order();
            order.setId(i);
            orderList.add(order);
        }

        when(orderRepository.findByUserIdAndStatuses(any(), any())).then(invocation -> orderList);

        assertNotNull(orderHistoryService.showOrderHistory(1, new Integer[]{1, 2}));
        assertEquals(0, orderHistoryService.showOrderHistory(1, new Integer[]{1, 2}).get(0).getId());
    }
}