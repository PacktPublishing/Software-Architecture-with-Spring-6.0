package com.packtpub.productservices.adapter.datasources.product;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.tomcat.util.codec.binary.Base64;

@Data
@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private byte[] photo;
    @JoinColumn(name = "user_id")
    private Integer userId;

    public String getPhotoBase64() {
        return Base64.encodeBase64String(this.photo);
    }

}

