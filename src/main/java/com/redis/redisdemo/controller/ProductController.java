package com.redis.redisdemo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.redis.redisdemo.entity.Product;
import com.redis.redisdemo.service.ProductService;

import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;
    
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Long id = productService.createProduct(product);
        URI uri = null;
        try{
            uri = new URI("/product/" + id);
        } catch (URISyntaxException e){
            throw new RuntimeException();
        }

        return ResponseEntity.created(uri).build();
    }
    
}
