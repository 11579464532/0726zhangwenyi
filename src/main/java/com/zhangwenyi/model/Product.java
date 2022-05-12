package com.zhangwenyi.model;

import java.sql.Blob;

public class Product {
    private Integer ProductId;
    private Integer CategoryId;
    private String ProductName;
    private String ProductDescription;
    private Blob picture;
    private Double price;

    public Product(Integer categoryId, String productName, String productDescription, Blob picture, Double price) {
        CategoryId = categoryId;
        ProductName = productName;
        ProductDescription = productDescription;
        this.picture = picture;
        this.price = price;
    }

    public Product() {}

    public Integer getProductId() {
        return ProductId;
    }

    public void setProductId(Integer productId) {
        ProductId = productId;
    }

    public Integer getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(Integer categoryId) {
        CategoryId = categoryId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        ProductDescription = productDescription;
    }

    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ProductId=" + ProductId +
                ", CategoryId=" + CategoryId +
                ", ProductName='" + ProductName + '\'' +
                ", ProductDescription='" + ProductDescription + '\'' +
                ", picture=" + picture +
                ", price=" + price +
                '}';
    }
}
