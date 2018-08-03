package com.example.printpatterns.data;

import com.example.printpatterns.domain.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findAll();

    Product findByProductId(Long productId);
}
