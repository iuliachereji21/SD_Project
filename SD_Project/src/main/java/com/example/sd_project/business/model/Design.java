package com.example.sd_project.business.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="design")
public class Design {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String title;

    @Column
    private Boolean isPublic;

    @Column
    private Date dateAndTime;

    @ManyToMany(mappedBy = "designs_it_appears_in")
    private List<Product> products;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;


    public Design(long id, String title, Boolean isPublic, Date dateAndTime, Customer customer) {
        this.id = id;
        this.title = title;
        this.isPublic = isPublic;
        this.dateAndTime = dateAndTime;
        this.customer = customer;
    }

    public Design(String title, Boolean isPublic, Date dateAndTime, Customer customer) {
        this.title = title;
        this.isPublic = isPublic;
        this.dateAndTime = dateAndTime;
        this.customer = customer;
    }

    public Design() {
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
