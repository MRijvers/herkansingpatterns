package com.example.printpatterns.data;

import com.example.printpatterns.domain.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
