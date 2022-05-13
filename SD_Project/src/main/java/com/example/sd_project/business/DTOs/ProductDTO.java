package com.example.sd_project.business.DTOs;


import com.example.sd_project.business.model.Product;

public class ProductDTO {
    private long id;
    private String name;
    private String price;
    private String category;
    private String link;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ProductDTO(Product product) {
        this.id=product.getId();
        this.category= product.getCategory();
        this.link = product.getLink();
        this.name = product.getName();
        this.price = String.valueOf(product.getPrice());
    }

    public ProductDTO() {
    }
}
