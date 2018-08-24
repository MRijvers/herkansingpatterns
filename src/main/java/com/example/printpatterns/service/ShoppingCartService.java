package com.example.printpatterns.service;

import com.example.printpatterns.domain.entity.ShoppingCart;
import org.springframework.stereotype.Service;

@Service
public interface ShoppingCartService {

    ShoppingCart save(ShoppingCart shoppingCart);
}
