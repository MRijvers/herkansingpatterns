package com.example.printpatterns.presentation.controller;

import com.example.printpatterns.domain.entity.Order;
import com.example.printpatterns.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShoppingCartController {

    @Autowired
    private OrderService orderService;

    private ModelAndView modelAndView = new ModelAndView();

    @RequestMapping(value = {"/shoppingCart/cart"}, method = RequestMethod.GET)
    public ModelAndView ShowShoppingCart(Long cartId){
        if(cartId == null) {
            Order cart = new Order();
            modelAndView.addObject("cart", cart);
        } else {
            Order cart = orderService.findByOrderId(cartId);
            modelAndView.addObject("cart", cart);
        }
        modelAndView.setViewName("shoppingCart/cart");
        return modelAndView;
    }

    @RequestMapping(value = {"/shoppingCart/{cartId}/{productId}"}, method = RequestMethod.POST)
    public ModelAndView addProductToCart(@PathVariable("cartId") Long cartId, @PathVariable("productId") Long productId) {
        Order cart = orderService.findByOrderId(cartId);
        modelAndView.addObject("cart", cart);
        orderService.addOrderItem(cart,productId);
        return ShowShoppingCart(cartId);
    }

    @RequestMapping(value = {"/shoppingCart/{cartId}/{productId}"}, method = RequestMethod.DELETE)
    public ModelAndView removeProductFromCart(@PathVariable("cartId") Long cartId, @PathVariable("productId") Long productId){
        Order cart = orderService.findByOrderId(cartId);
        orderService.deleteOrderItem(cart,productId);
        return ShowShoppingCart(cartId);
    }
/*
    @RequestMapping( value = {"/checkout"}, method = RequestMethod.GET)
    public ModelAndView submitCart() {

        return ShowShoppingCart();
    } */
}
