package com.packtpub.onlineauction.service.impl;

import com.packtpub.onlineauction.entity.Product;
import com.packtpub.onlineauction.repository.ProductRepository;
import com.packtpub.onlineauction.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void saveProduct(Product product, MultipartFile file) throws IOException {
        product.setPhoto(file.getBytes());
        productRepository.save(product);
    }

}
