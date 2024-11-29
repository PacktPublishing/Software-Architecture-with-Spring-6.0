package com.packtpub.productservices.adapter.datasources.product.service;

import com.packtpub.productservices.adapter.transportlayers.restapi.dto.request.ProductRequest;
import com.packtpub.productservices.adapter.transportlayers.restapi.dto.response.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<ProductResponse> getAllProducts();
    ProductResponse getProductById(Long id);
    ProductResponse add(ProductRequest productRequest);
}
