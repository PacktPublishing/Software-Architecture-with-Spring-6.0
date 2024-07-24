package com.packtpub.productservices.internal.repositories;

import com.packtpub.productservices.internal.entity.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
}

