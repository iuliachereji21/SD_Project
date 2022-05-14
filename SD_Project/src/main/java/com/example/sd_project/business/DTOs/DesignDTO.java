package com.example.sd_project.business.DTOs;

import com.example.sd_project.business.model.Design;
import com.example.sd_project.business.model.Product;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class DesignDTO {
    private long id;
    private String title;
    private Boolean isPublic;
    private Date dateAndTime;
    private List<ProductDTO> products;
    private long customerId;

    public DesignDTO() {
    }
    public DesignDTO(Design design) {
        this.id=design.getId();
        this.title= design.getTitle();
        this.isPublic=design.getIsPublic();
        this.dateAndTime=design.getDateAndTime();
        this.customerId=design.getCustomer().getId();

        this.products=new ArrayList<>();
        for(Product product: design.getProducts()){
            this.products.add(new ProductDTO(product));
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    public Date getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Date dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
}
