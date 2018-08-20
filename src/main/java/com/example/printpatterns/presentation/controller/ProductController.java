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

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCatalogService productCatalogService;

    private ModelAndView modelAndView = new ModelAndView();

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
    public ModelAndView productConfiguration(){
        Iterable<Product> products = productService.findAll();
        Product p = new Product();
        modelAndView.addObject("products", products);
        modelAndView.addObject("product", p);
        modelAndView.setViewName("admin/products/productConfiguration");
        return modelAndView;
    }

    @RequestMapping(value = {"admin/products/productCreation"}, method = RequestMethod.POST)
    public ModelAndView productCreation(@Valid @ModelAttribute("Product")Product product, BindingResult bindingResult, Model uiModel){
        return createOrUpdateProduct(true, product, bindingResult, uiModel);
    }

    @RequestMapping(value="admin/products/productUpdate/{productId}", method = RequestMethod.GET)
    public String productUpdateForm(@PathVariable(required = false, name = "productId") Long productId, Model uiModel){
        if (productId != null){
            uiModel.addAttribute("product", productService.findByProductId(productId));
        } else {
            uiModel.addAttribute("product", new Product());
        }
        return "/admin/products/productConfiguration";
    }

    @RequestMapping(value={"admin/products/productUpdate"}, method = RequestMethod.POST)
    public ModelAndView productUpdate(@Valid Product product, BindingResult bindingResult, Model uiModel){
        return createOrUpdateProduct(false, product, bindingResult, uiModel);
    }

    private ModelAndView createOrUpdateProduct(boolean isCreate, Product product, BindingResult bindingResult, Model uiModel){
        if(bindingResult.hasErrors()){
            uiModel.addAttribute("product",product);
            return list();
        }
        uiModel.asMap().clear();

        if(isCreate){
            productService.save(product);
        } else {
            Product existingProduct = productService.findByProductId(product.getProductId());
            assert existingProduct != null : "product should exist";
            existingProduct.updateEditableFields(product);
            productService.save(product);
        }
        return list();
    }

    @RequestMapping(value = {"admin/products/productRemoval/{productId}"}, method = RequestMethod.GET)
    public ModelAndView productRemoval(@PathVariable(name = "productId") Long productId) {
        productService.deleteByProductId(productId);
        return list();
    }

    @RequestMapping(value = {"products/productOverview"}, method = RequestMethod.GET)
    public ModelAndView productOverview(){
        Iterable<Product> products = productService.findAll();
        modelAndView.addObject("products", products);
        modelAndView.setViewName("products/productOverview");
        return modelAndView;
    }
}
