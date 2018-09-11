package com.example.printpatterns.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

// Created by Brian de Liefde

@Getter
@Setter
@Entity
public class Order implements Serializable {

    @Id
    @GeneratedValue
    private long orderId;

    private Date orderDate;

    @OneToMany(cascade = CascadeType.ALL)
    private Map<Long, OrderItem> orderMap = new HashMap<>();

    //Add orderItem to Order
    public void addOrderItem(Product product){
        Long productId = product.getProductId();
        if(orderMap.containsKey(productId)){
            OrderItem orderItem = orderMap.get(productId);
            orderItem.incrementQuantity();
            orderMap.put(productId, orderItem);
        } else {
            OrderItem orderItem = new OrderItem(product, 1);
            orderMap.put(productId, orderItem);
        }
    }

    //Remove an orderItem from Order
    public void removeOrderItem(Long orderItemKey){
        if(orderMap.containsKey(orderItemKey)){
            OrderItem orderItem = orderMap.get(orderItemKey);
            if(orderItem.getQuantity() > 1){
                orderItem.decrementQuantity();
                orderMap.put(orderItemKey,orderItem);
            } else {
                orderMap.remove(orderItemKey);
            }
        }
    }

    public double getTotal(){
        Collection<OrderItem> orderItems = orderMap.values();
        double total = 0.00;
        for (OrderItem scItem : orderItems) {
            Product product = scItem.getProduct();
            total += (scItem.getQuantity() * product.getPrice());
        }
        return total;
    }
}
