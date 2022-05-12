package com.example.sd_project.business.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private float price;

    @Column
    private String category;

    @Column
    private String link;

    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="design_product",
            joinColumns = @JoinColumn(name="product_id"),
            inverseJoinColumns = @JoinColumn(name="design_id")
    )
    private List<Design> designs_it_appears_in;

    public Product(long id, String name, float price, String category, String link, Store store) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.link = link;
        this.store = store;
    }

    public Product(String name, float price, String category, String link, Store store) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.link = link;
        this.store = store;
    }

    public Product() {
    }

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
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

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<Design> getDesigns_it_appears_in() {
        return designs_it_appears_in;
    }

    public void setDesigns_it_appears_in(List<Design> designs_it_appears_in) {
        this.designs_it_appears_in = designs_it_appears_in;
    }
}
