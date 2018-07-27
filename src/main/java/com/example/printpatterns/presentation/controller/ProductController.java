package com.example.printpatterns.presentation.controller;

import com.example.printpatterns.domain.entity.Product;
import com.example.printpatterns.domain.entity.ProductCatalog;
import com.example.printpatterns.service.ProductCatalogService;
import com.example.printpatterns.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCatalogService productCatalogService;

    private ModelAndView modelAndView = new ModelAndView();

    public ProductController(ProductService productService, ProductCatalogService productCatalogService){
        this.productCatalogService = productCatalogService;
        this.productService = productService;
    }

    public void createProductCatalog(){
        ProductCatalog productCatalog = new ProductCatalog();
        productCatalog = productCatalogService.save(productCatalog);
    }

    @Transactional
    @RequestMapping(value = {"admin/products/list"}, method = RequestMethod.GET)
    public ModelAndView list(){
        Iterable<Product> products = productService.findAll();
        modelAndView.addObject("products", products);
        modelAndView.setViewName("admin/products/list");
        return modelAndView;
    }

    @RequestMapping(value = {"admin/products/productConfiguration"}, method = RequestMethod.GET)
    public ModelAndView createProduct(){
        Iterable<Product> products = productService.findAll();
        Product p = new Product();
        modelAndView.addObject("products", products);
        modelAndView.addObject("product", p);
        modelAndView.setViewName("admin/products/productConfiguration");
        return modelAndView;
    }

    @RequestMapping(value = {"admin/products/productCreation"}, method = RequestMethod.POST)
    public String productSubmit(@Valid @ModelAttribute("Product")Product product, BindingResult result, Model uiModel){
        if (result.hasErrors()){
            productService.save(product);
            return "error";
        }
        productService.save(product);
        return "redirect:/admin/products/list";
    }

    /*
    @RequestMapping(value={"admin/products/productUpdate/{productId}"}, method = RequestMethod.PUT)
    public String productUpdate(@PathVariable("productId") Long productId, Model uiModel){
        Product product = productService.findByProductId(productId);
        uiModel.addAttribute("product", product);


    } */

    @RequestMapping(value = {"admin/products/productRemoval/{productId}"}, method = RequestMethod.DELETE)
    public String productRemoval(@PathVariable("productId") Long productId, Model model, HttpServletResponse response) {
        productService.removeByProductId(productId);
        return "redirect:/admin/products/list";
    }
}
