package com.packtpub.clienapplication;


public class ProductResponse {
    private Integer id;
    private String name;
    private String description;
    private Integer userId;
    private String photoBase64;

    public ProductResponse() {
    }

    public ProductResponse(Integer id, String name, String description, Integer userId, String photoBase64) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userId = userId;
        this.photoBase64 = photoBase64;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhotoBase64() {
        return photoBase64;
    }

    public void setPhotoBase64(String photoBase64) {
        this.photoBase64 = photoBase64;
    }
}