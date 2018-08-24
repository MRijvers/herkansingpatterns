package com.example.printpatterns.service.serviceImpl;

import com.example.printpatterns.data.ShoppingCartRepository;
import com.example.printpatterns.domain.entity.ShoppingCart;
import com.example.printpatterns.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("shoppingCartService")
@Repository
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Transactional
    public ShoppingCart save(ShoppingCart shoppingCart) {
        shoppingCartRepository.save(shoppingCart);
        shoppingCartRepository.flush();
        shoppingCart.clear();
        return shoppingCart;
    }
}
