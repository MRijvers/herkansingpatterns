package com.example.printpatterns.service.serviceimpl;

import com.example.printpatterns.domain.Product;
import com.example.printpatterns.repositories.ProductRepository;
import com.example.printpatterns.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll()
    {
        return productRepository.findAll();
    }

    @Override
    public Product findProductByProductCode(Long productCode)
    {
        return productRepository.findProductByProductCode(productCode);
    }
}
