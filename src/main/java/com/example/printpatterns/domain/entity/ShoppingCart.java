package com.example.printpatterns.domain.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.awt.color.ProfileDataException;
import java.io.Serializable;
import java.util.*;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class ShoppingCart implements Serializable {

    private HashMap<Long ,ShoppingCartItem> cartMap = new HashMap<>();

    public synchronized void addProduct(Long id, Product product){
        ShoppingCartItem cartItem = new ShoppingCartItem();
        if(checkProductInCart(id)){
            cartItem = cartMap.get(id);
            cartItem.incrementQuantity();
            cartMap.put(id, cartItem);
        } else {
            cartItem.setQuantity(1);
            cartItem.setProduct(product);
            cartMap.put(id, cartItem);
        }
    }

    public synchronized void removeProduct(Long productId){
        if(cartMap.containsKey(productId)){
            ShoppingCartItem cartItem = cartMap.get(productId);
            if(cartItem.getQuantity() <= 1){
                cartMap.remove(productId);
            } else {
                cartItem.decrementQuantity();
                cartMap.put(productId,cartItem);
            }
        }
    }

    public synchronized void clear(){
        cartMap.clear();
    }

    public synchronized List<ShoppingCartItem> getItems(){
        return new LinkedList<>(this.cartMap.values());
    }

    public synchronized double getTotal(){
        double total = 0.0;
        for (ShoppingCartItem scItem : getItems()) {
            Product product = scItem.getProduct();
            total += (scItem.getQuantity() * product.getPrice());
        }
        return total;
    }

    private boolean checkProductInCart(Long productId){
        if(cartMap.isEmpty()) {
            return false;
        } else {
            return cartMap.containsKey(productId);
        }
    }

}
