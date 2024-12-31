package com.packtpub.productservices.internal.entity;

import org.apache.tomcat.util.codec.binary.Base64;

public class Product {
    private Integer id;
    private String name;
    private String description;
    private byte[] photo;
    private Integer userId;
    private String photoBase64;

    public Product(Integer id, String name, String description, byte[] photo, Integer userId, String photoBase64) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.userId = userId;
        this.photoBase64 = photoBase64;
    }

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setPhotoBase64(String photoBase64) {
        this.photoBase64 = photoBase64;
    }

    public String getPhotoBase64() {
        return Base64.encodeBase64String(this.photo);
    }

}

