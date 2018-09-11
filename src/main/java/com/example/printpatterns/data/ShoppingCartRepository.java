package com.example.printpatterns.data;

import com.example.printpatterns.domain.entity.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends CrudRepository<ShoppingCart, Long> {
    ShoppingCart save(ShoppingCart shoppingCart);
    void flush();
}
