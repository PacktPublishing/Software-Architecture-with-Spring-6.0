package com.packtpub.productservices.adapter.datasources.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.codec.binary.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

