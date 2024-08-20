package com.packtpub.onlineauction.etlbatchprocess.products;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ProductItemProcessor implements ItemProcessor<ProductDto, Product> {

    public Product process(ProductDto productDto) throws Exception {
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .photo(productDto.getPhoto())
                .userId(productDto.getUserId())
                .build();
    }
}