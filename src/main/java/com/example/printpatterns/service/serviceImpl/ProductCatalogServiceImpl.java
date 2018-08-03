package com.example.printpatterns.service.serviceImpl;

import com.example.printpatterns.domain.entity.ProductCatalog;
import com.example.printpatterns.service.ProductCatalogService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("productCatalogService")
@Repository
@Transactional
public class ProductCatalogServiceImpl implements ProductCatalogService {

    @Override
    public ProductCatalog save(ProductCatalog productCatalog) {
        return null;
    }
}
