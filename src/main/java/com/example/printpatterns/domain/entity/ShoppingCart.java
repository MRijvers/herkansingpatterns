package com.example.printpatterns.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.awt.color.ProfileDataException;
import java.io.Serializable;
import java.util.*;

@Component
@Getter
@Setter
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class ShoppingCart implements Serializable {

    private Long ShoppingCartId;
    private HashMap<Long ,ShoppingCartItem> cartMap = new HashMap<>();

    // Toevoegen van een product
    // Check op al bestaan in shoppingcart +1 quantity
    // *** Nog toevoegen outofstock
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
