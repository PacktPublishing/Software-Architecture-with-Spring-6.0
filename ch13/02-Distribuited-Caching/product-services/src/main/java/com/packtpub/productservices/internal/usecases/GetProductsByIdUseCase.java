package com.packtpub.productservices.internal.usecases;

import com.packtpub.productservices.internal.entity.Product;
import com.packtpub.productservices.internal.repositories.ProductRepository;

public class GetProductsByIdUseCase {
    private final ProductRepository productRepository;

    public GetProductsByIdUseCase(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product execute(Long id) {
        return productRepository.findById(id);
    }

}