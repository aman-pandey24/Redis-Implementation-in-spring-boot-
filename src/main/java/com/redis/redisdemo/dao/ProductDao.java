package com.redis.redisdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.redis.redisdemo.entity.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Long>{
    
}
