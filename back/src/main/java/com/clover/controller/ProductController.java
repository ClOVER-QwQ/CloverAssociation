package com.clover.controller;

import com.clover.pojo.Product;
import com.clover.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController("productController")
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("getAll")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("getBasicAll")
    public List<Product> getBasicAll() {
        return productService.getBasicAll();
    }

    @GetMapping("get/{id}")
    public Map<String,Object> getById(@PathVariable int id) {
        return productService.getById(id);
    }
}
