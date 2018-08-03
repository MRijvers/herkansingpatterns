package com.example.printpatterns.service.serviceImpl;

import com.example.printpatterns.data.ProductRepository;
import com.example.printpatterns.domain.entity.Product;
import com.example.printpatterns.exception.NotEnoughProductsInStockException;
import com.example.printpatterns.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service("shoppingCartService")
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
@Slf4j
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ProductRepository productRepository;

    private Map<Product, Integer> products = new HashMap<>();

    @Transactional
    public void addProduct(Product product) {
        if(products.containsKey(product)){
            products.replace(product, products.get(product) + 1);
        } else {
            products.put(product,1);
        }
    }

    @Transactional
    public void removeProduct(Product product) {
        if(products.containsKey(product)){
            if(products.get(product) > 1) {
                products.replace(product, products.get(product) - 1);
            }
            else if (products.get(product) == 1){
                products.remove(product);
            }
        }
    }

    @Transactional
    public Map<Product, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(products);
    }

    @Transactional
    public BigDecimal getTotal() {
        return products.entrySet().stream().map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue()))).reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }

    @Transactional
    public void checkout() throws NotEnoughProductsInStockException {
        Product product;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            // Refresh quantity for every product before checking
            product = productRepository.findOneByProductId(entry.getKey().getProductId());
            if (product.getStock() < entry.getValue())
                throw new NotEnoughProductsInStockException(product);
            entry.getKey().setStock(product.getStock() - entry.getValue());
        }

        productRepository.save(products.keySet());
        productRepository.flush();
        products.clear();
    }
}
