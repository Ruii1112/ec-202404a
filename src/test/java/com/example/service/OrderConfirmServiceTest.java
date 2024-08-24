package com.example.service;

import com.example.domain.Order;
import com.example.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

/**
 * {@link OrderConfirmService}のテスト
 */

@ExtendWith(MockitoExtension.class)
class OrderConfirmServiceTest {

    @InjectMocks
    OrderConfirmService orderConfirmService;

    @Mock
    OrderRepository orderRepository;

    @DisplayName("idからorderインスタンスの取得")
    @Test
    void showConfirmOrder() {
        Order order = new Order();
        order.setUserId(1);

        when(orderRepository.findById(anyInt())).then(invocation -> order);

        assertNotNull(orderConfirmService.showConfirmOrder(1));
        assertEquals(1, orderConfirmService.showConfirmOrder(1).getUserId());
    }
}