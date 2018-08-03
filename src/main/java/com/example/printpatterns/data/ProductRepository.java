package com.example.printpatterns.data;

import com.example.printpatterns.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();

    Product findByProductId(Long productId);

    Product findOneByProductId(Long productId);

    Set<Product> save(Set<Product> product);
}
