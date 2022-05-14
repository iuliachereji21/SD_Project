package com.example.sd_project.business.DTOs;

public class LogInDTO {
    private String email;
    private String password;

    public LogInDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LogInDTO() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
