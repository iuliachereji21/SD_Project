package com.example.sd_project.business.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name="customer")
public class Customer extends User{

    @Column
    private String phone;

    @Column
    private String occupation;

    @OneToMany(mappedBy = "customer")
    private List<Design> designs;

    public Customer(String name, String email, String password, String phone, String occupation) {
        super(name, email, password);
        this.phone = phone;
        this.occupation = occupation;
    }

    public Customer(long id, String name, String email, String password, String phone, String occupation) {
        super(id, name, email, password);
        this.phone = phone;
        this.occupation = occupation;
    }

    public Customer(String phone, String occupation) {
        this.phone = phone;
        this.occupation = occupation;
    }

    public Customer(){}

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public List<Design> getDesigns() {
        return designs;
    }

    public void setDesigns(List<Design> designs) {
        this.designs = designs;
    }
}
