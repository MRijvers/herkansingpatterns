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

    public synchronized void updateQuantity(Long productId, int quantity, Product p) {
        if(cartMap.containsKey(productId)) {
            ShoppingCartItem cartItem = cartMap.get(productId);
            cartItem.setQuantity(quantity);
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
        List<ShoppingCartItem> listOfCartItems = new ArrayList<>();
        listOfCartItems.addAll(this.cartMap.values());
        return listOfCartItems;
    }

    public synchronized double getTotal(){
        double total = 0.0;
        Iterator<ShoppingCartItem> scItemIterator = getItems().iterator();
        while (scItemIterator.hasNext()){
            ShoppingCartItem scItem = scItemIterator.next();
            Product product = scItem.getProduct();
            total += (scItem.getQuantity() * product.getPrice());
        }
        return total;
    }

    public boolean checkProductInCart(Long productId){
        if(cartMap.isEmpty()) {
            return false;
        } else {
            if (cartMap.containsKey(productId)) {
                return true;
            }
        }
        return false;
    }

}
