package com.example.printpatterns.exception;

import com.example.printpatterns.domain.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;

public class NotEnoughProductsInStockException extends Exception {

    @Autowired
    private static final String DEFAULT_MESSAGE = "Not enough products in stock";

    public NotEnoughProductsInStockException(Product product) {
        super(String.format("Not enough %s products in stock. Only %d left", product.getProductName(), product.getStock()));
    }
}
