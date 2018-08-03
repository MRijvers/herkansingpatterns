package com.example.printpatterns.presentation.controller;

import com.example.printpatterns.service.ProductService;
import com.example.printpatterns.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ProductService productService;

    private ModelAndView modelAndView = new ModelAndView();

    @RequestMapping(value = {"shoppingCart/cart"}, method = RequestMethod.GET)
    public ModelAndView shoppingCart(){
        modelAndView.addObject("products", shoppingCartService.getProductsInCart());
        modelAndView.addObject("total", shoppingCartService.getTotal().toString());
        modelAndView.setViewName("shoppingCart/cart");
        return modelAndView;
    }

    @RequestMapping(value = {"shoppingCart/cart/addProduct/{productId}"}, method = RequestMethod.POST)
    public ModelAndView addProductToCart(@PathVariable("productId") Long productId) {
        shoppingCartService.addProduct(productService.findByProductId(productId));
        return shoppingCart();
    }

    @RequestMapping(value = {"shoppingCart/cart/removeProduct/{productId}"}, method = RequestMethod.POST)
    public ModelAndView removeProductFromCart(@PathVariable("productId") Long productId){
        shoppingCartService.removeProduct(productService.findByProductId(productId));
        return shoppingCart();
    }
}
