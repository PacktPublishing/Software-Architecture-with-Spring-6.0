package com.packtpub.onlineauction.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.commons.codec.binary.Base64;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private byte[] photo;

    public String photoBase64() {
        return Base64.encodeBase64String(this.photo);
    }

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}

