package com.example.printpatterns.data;

import com.example.printpatterns.domain.entity.ProductCatalog;
import org.springframework.data.repository.CrudRepository;

public interface ProductCatalogRepository extends CrudRepository<ProductCatalog, Long> {
}
