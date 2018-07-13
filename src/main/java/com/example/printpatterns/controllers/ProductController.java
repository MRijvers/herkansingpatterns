package com.example.printpatterns.controllers;

import com.example.printpatterns.domain.Product;
import com.example.printpatterns.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProductController
{
    @Autowired
    private ProductService productService;

    ModelAndView modelAndView = new ModelAndView();

    @RequestMapping(value = {"/productsOverview"}, method = RequestMethod.GET)
    public ModelAndView viewProducts()
    {
        List<Product> products = productService.findAll();
        if(products != null)
            modelAndView.addObject("products", products);

        modelAndView.setViewName("productsOverview");
        return modelAndView;
    }
}
