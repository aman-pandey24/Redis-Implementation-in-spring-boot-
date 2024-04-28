package com.redis.redisdemo.service;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.redis.redisdemo.dao.ProductDao;
import com.redis.redisdemo.entity.Product;

@Service
public class ProductService {
    
    @Autowired
    ProductDao productDao;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public Product getById(Long id){
        String key = "product:" + id;
        Product product = (Product)redisTemplate.opsForValue().get(key);
        if(product != null){
            return product;
        }
        product = productDao.findById(id).get();
        redisTemplate.opsForValue().set(key, product, 5, TimeUnit.MINUTES);
        return product;
    }

    public Long createProduct(Product product){
        product = productDao.save(product);
        return product.getId();
    }
}
