package com.example.printpatterns.presentation.controller;

import com.example.printpatterns.domain.entity.Product;
import com.example.printpatterns.domain.entity.ShoppingCart;
import com.example.printpatterns.service.ProductService;
import com.example.printpatterns.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {


    @Autowired
    private ProductService productService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    private ModelAndView modelAndView = new ModelAndView();

    @RequestMapping(value = {"/cart"}, method = RequestMethod.GET)
    public ModelAndView shoppingCart(HttpSession session){
        if(session.getAttribute("cart") == null) {
            ShoppingCart cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        } else {
            ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
            modelAndView.addObject("products", cart.getItems());
            modelAndView.addObject("total", cart.getTotal());

        }
        modelAndView.setViewName("shoppingCart/cart");
        return modelAndView;
    }

    @RequestMapping(value = {"/cart/addProduct/{productId}"}, method = RequestMethod.GET)
    public ModelAndView addProductToCart(@PathVariable("productId") Long productId, HttpSession session) {
            Product newProduct = productService.findByProductId(productId);
        if (session.getAttribute("cart") == null) {
            ShoppingCart cart = new ShoppingCart();
            cart.addProduct(productId, newProduct);
            session.setAttribute("cart", cart);
        } else {
            ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
            cart.addProduct(productId, newProduct);
        }
            return shoppingCart(session);
    }

    @RequestMapping(value = {"/cart/removeProduct/{productId}"}, method = RequestMethod.GET)
    public ModelAndView removeProductFromCart(@PathVariable("productId") Long productId, HttpSession session){
        ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
        cart.removeProduct(productId);
        return shoppingCart(session);
    }

    @RequestMapping( value = {"/cart/checkout"}, method = RequestMethod.GET)
    public ModelAndView checkout(HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        shoppingCartService.save(cart);
        modelAndView.addObject("cart", cart);
        modelAndView.setViewName("shoppingCart/checkout");
        return modelAndView;
    }
}
