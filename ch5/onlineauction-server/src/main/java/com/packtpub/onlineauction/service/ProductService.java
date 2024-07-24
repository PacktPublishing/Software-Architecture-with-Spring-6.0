package com.packtpub.onlineauction.service;

import com.packtpub.onlineauction.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Optional<List<Product>> getAllProducts();
    void saveProduct(Product product, MultipartFile file) throws IOException;
    Optional<Product> getProductById(Integer id);
}
