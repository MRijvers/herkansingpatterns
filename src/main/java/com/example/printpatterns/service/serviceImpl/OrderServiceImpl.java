package com.example.printpatterns.service.serviceImpl;

import com.example.printpatterns.data.OrderRepository;
import com.example.printpatterns.data.ProductRepository;
import com.example.printpatterns.domain.entity.Order;
import com.example.printpatterns.domain.entity.Product;
import com.example.printpatterns.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("orderService")
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public void addOrderItem(Order order, long productId) {
        Product product = productRepository.findByProductId(productId);
        order.addOrderItem(product);
    }

    public void deleteOrderItem(Order order, long productId) {
        order.removeOrderItem(productId);
    }

    public void submitOrder(Order order) {

    }

    @Transactional
    public Order findByOrderId(Long orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    public List<Order> findAll() {
        return null;
    }
}
