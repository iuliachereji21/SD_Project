package com.example.sd_project.business.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="admin")
public class Admin extends User{

    @ManyToOne
    @JoinColumn(name="store_id")
    private Store store;

    public Admin(String name, String email, String password) {
        super(name, email, password);
    }

    public Admin(long id, String name, String email, String password) {
        super(id, name, email, password);
    }

    public Admin() {
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
