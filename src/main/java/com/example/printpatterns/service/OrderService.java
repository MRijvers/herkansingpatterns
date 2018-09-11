package com.example.printpatterns.service;

import com.example.printpatterns.domain.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;

// Created by Brian de Liefde

@Service
public interface OrderService {

    List<Order> findAll();

    Order findByOrderId(Long orderId);

    Order saveOrder(Order order);

    void addOrderItem(Order order, long productId);

    void deleteOrderItem(Order order, long productId);

    void submitOrder(Order order);

}
