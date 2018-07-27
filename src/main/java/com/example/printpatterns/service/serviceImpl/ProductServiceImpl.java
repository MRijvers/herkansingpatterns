package com.example.printpatterns.service.serviceImpl;

import com.example.printpatterns.data.ProductRepository;
import com.example.printpatterns.domain.entity.Product;
import com.example.printpatterns.service.ProductService;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("productService")
@Transactional
// @Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional
    public Product findByProductId(Long id) {
        return productRepository.findByProductId(id);
    }

    @Transactional
    public Product removeByProductId(Long id) {
        return productRepository.removeByProductId(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
}
