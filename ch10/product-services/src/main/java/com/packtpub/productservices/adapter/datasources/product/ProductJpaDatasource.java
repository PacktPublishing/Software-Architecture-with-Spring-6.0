package com.packtpub.productservices.adapter.datasources.product;

import com.packtpub.productservices.internal.entity.Product;
import com.packtpub.productservices.internal.repositories.ProductRepository;

import java.util.List;

public class ProductJpaDatasource implements ProductRepository {

    private final ProductJpaRepository productDatabaseRepository;

    public ProductJpaDatasource(ProductJpaRepository productDatabaseRepository) {
        this.productDatabaseRepository = productDatabaseRepository;
    }

    @Override
    public List<Product> findAll() {
        return productDatabaseRepository.findAll().stream().map(this::toDomain).toList();
    }

    private Product toDomain(ProductEntity productEntity) {
        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getDescription(),
                null,
                productEntity.getUserId(),
                productEntity.getPhotoBase64()
        );
    }


}
