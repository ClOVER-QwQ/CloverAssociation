package com.clover.service;

import com.clover.pojo.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<Product> getAll();
    List<Product> getBasicAll();
    Map<String,Object> getById(int id);
}
