package com.example.printpatterns.domain.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.awt.color.ProfileDataException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@Component
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "session")
public class ShoppingCart implements Serializable {

    private List<ShoppingCartItem> cartCollection = new LinkedList<ShoppingCartItem>();

    public synchronized void addProduct(Product product, int quantity){
        ShoppingCartItem newItem = new ShoppingCartItem();
        if(checkProductInCart(product)){
            ShoppingCartItem existProd = cartCollection.get().getProduct().getProductId().equals(product.getProductId());
            existProd.incrementQuantity();
            cartCollection.add(existProd);
        } else {
            cartMap.put(productId, newItem);
        }
    }

    public synchronized void updateQuantity(Long productId, int quantity, Product p) {
        if(cartMap.containsKey(productId)) {
            ShoppingCartItem cartItem = (ShoppingCartItem) cartMap.get(productId);
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
        List<ShoppingCartItem> listOfCartItems = new ArrayList<ShoppingCartItem>();
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

    public boolean checkProductInCart(Product product){
        if(cartCollection.isEmpty()) {
            return false;
        } else {
            if (cartCollection.get()) {
                return true;
            }
        }
        return false;
    }

}
