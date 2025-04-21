package com.packtpub.productservices.adapter.datasources.product;

import com.packtpub.productservices.internal.entity.Product;
import com.packtpub.productservices.internal.exception.NotFoundException;
import com.packtpub.productservices.internal.repositories.ProductRepository;
import org.springframework.cache.annotation.Cacheable;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

public class ProductJpaDatasource implements ProductRepository {

    private final ProductJpaRepository productDatabaseRepository;

    public ProductJpaDatasource(ProductJpaRepository productDatabaseRepository) {
        this.productDatabaseRepository = productDatabaseRepository;
    }

    @Override
    public List<Product> findAll() {
        return productDatabaseRepository.findAll().stream().map(this::toDomain).toList();
    }

    @Override
    public Product findById(Long id) {
        Optional<ProductEntity> product = Optional.ofNullable(productDatabaseRepository.findById(id).orElseThrow(() -> new NotFoundException("")));
        return toDomain(product.get());
    }

    @Override
    public Product add(Product product) {
        return toDomain(productDatabaseRepository.save(toEntity(product)));
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

    private ProductEntity toEntity(Product product) {
        return new ProductEntity(
                product.getId(),
                product.getName(),
                product.getDescription(),
                Base64.getEncoder().encode(product.getPhotoBase64().getBytes()),
                product.getUserId()
        );
    }
}
