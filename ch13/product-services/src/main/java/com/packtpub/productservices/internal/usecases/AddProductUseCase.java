package com.packtpub.productservices.internal.usecases;

import com.packtpub.productservices.internal.entity.Product;
import com.packtpub.productservices.internal.repositories.ProductRepository;

import java.util.List;

public class AddProductUseCase {
    private final ProductRepository productRepository;

    public AddProductUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(Product product) {
        return productRepository.add(product);
    }

}