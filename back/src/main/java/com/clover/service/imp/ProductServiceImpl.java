package com.clover.service.imp;

import com.clover.dao.ProductDao;
import com.clover.pojo.Product;
import com.clover.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> getAll() {
        return productDao.selectProducts();
    }

    @Override
    public List<Product> getBasicAll() {
        return productDao.getBasicAll();
    }

    @Override
    public Map<String,Object> getById(int id) {
        return productDao.getById(id);
    }
}
