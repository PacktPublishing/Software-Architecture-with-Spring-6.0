package com.packtpub.onlineauction.service.impl;

import com.packtpub.onlineauction.entity.Product;
import com.packtpub.onlineauction.repository.ProductRepository;
import com.packtpub.onlineauction.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Optional<List<Product>> getAllProducts() {
        return Optional.of(productRepository.findAll());
    }

    public void saveProduct(Product product, MultipartFile file) throws IOException {
        product.setPhoto(file.getBytes());
        productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        return productRepository.findById(id);
    }

}
