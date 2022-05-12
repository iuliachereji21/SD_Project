package com.example.sd_project.business.DTOs;

public class UserDTO {
    private long userId;
    private boolean isCustomer;
    private String token;

    public UserDTO(long userId, boolean isCustomer, String token) {
        this.userId = userId;
        this.isCustomer=isCustomer;
        this.token=token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public boolean isCustomer() {
        return isCustomer;
    }

    public void setCustomer(boolean customer) {
        isCustomer = customer;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
