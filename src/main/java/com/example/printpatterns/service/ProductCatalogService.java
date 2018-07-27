package com.example.printpatterns.service;

import com.example.printpatterns.domain.entity.ProductCatalog;
import org.springframework.stereotype.Service;

@Service
public interface ProductCatalogService {

    ProductCatalog save(ProductCatalog productCatalog);
}
