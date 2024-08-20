package com.packtpub.onlineauction.etlbatchprocess.products;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Product {

    private Integer id;
    private String name;
    private String description;
    private byte[] photo;
    private Integer userId;

}
