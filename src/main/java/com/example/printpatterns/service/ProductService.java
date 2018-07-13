package com.example.printpatterns.service;

import com.example.printpatterns.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService
{
    List<Product> findAll();
    Product findProductByProductCode(Long productCode);
}
