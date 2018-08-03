package com.example.printpatterns.service;

import com.example.printpatterns.domain.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    List<Product> findAll();

    Product findByProductId(Long productId);

    void deleteByProductId(Long productId);

    Product save(Product product);
}
