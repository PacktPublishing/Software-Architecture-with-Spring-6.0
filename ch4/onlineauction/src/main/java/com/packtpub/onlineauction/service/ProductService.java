package com.packtpub.onlineauction.service;

import com.packtpub.onlineauction.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    void saveProduct(Product product, MultipartFile file) throws IOException;
    Product getProductById(Integer id);
}
