package com.example.printpatterns.service;

import com.example.printpatterns.domain.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product> findAll();

    Product findByProductId(Long id);

    Product removeByProductId(Long id);

    Product save(Product product);
}
