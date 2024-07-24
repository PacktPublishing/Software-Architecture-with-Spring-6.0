package com.packtpub.productservices.internal.usecases;

import com.packtpub.productservices.internal.entity.Product;
import com.packtpub.productservices.internal.repositories.ProductRepository;

import java.util.List;

public class GetProductsUseCase {
    private final ProductRepository productRepository;

    public GetProductsUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> execute() {
        return productRepository.findAll();
    }

}