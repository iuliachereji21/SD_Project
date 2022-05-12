package com.example.sd_project.business.service;

import com.example.sd_project.business.model.Admin;
import com.example.sd_project.business.model.Customer;
import com.example.sd_project.business.model.User;

public class Factory {

    public static User createUser(String userType){
        User user=null;
        switch(userType){
            case "Customer" :
                user = new Customer();
                break;
            case "Admin" :
                user = new Admin();
                break;
        }
        return user;
    }
}
