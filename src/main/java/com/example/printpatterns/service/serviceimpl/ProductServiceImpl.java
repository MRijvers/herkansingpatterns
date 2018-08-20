package com.example.printpatterns.service.serviceImpl;

import com.example.printpatterns.data.ProductRepository;
import com.example.printpatterns.domain.entity.Product;
import com.example.printpatterns.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service("productService")
@Repository
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public Product findByProductId(Long productId) {
        return productRepository.findByProductId(productId);
    }

    @Transactional
    public void deleteByProductId(Long productId) {
        productRepository.deleteById(productId);
    }

    @Transactional
    public Product save(Product product) {
        return productRepository.save(product);
    }
}
