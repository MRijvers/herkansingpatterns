package com.example.printpatterns.service;

import com.example.printpatterns.domain.entity.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public interface ShoppingCartService {

    void addProduct(Product product);

    void removeProduct(Product product);

    Map<Product, Integer> getProductsInCart();

    // void checkout() throws NotEnoughProductsInStockException;

    BigDecimal getTotal();
}
