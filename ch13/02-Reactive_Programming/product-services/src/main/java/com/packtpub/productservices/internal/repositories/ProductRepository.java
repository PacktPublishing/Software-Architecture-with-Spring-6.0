package com.packtpub.productservices.internal.repositories;

import com.packtpub.productservices.internal.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();
    Product findById(Long id);
    Product add(Product product);
}

