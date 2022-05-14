package com.example.sd_project.business.DTOs;

public class RegisterDTO {

    private String name;
    private String email;
    private String password;
    private String repeatedPassword;
    private String phone;
    private String occupation;

    public RegisterDTO(String name, String email, String password, String repeatedPassword, String phone, String occupation) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.repeatedPassword = repeatedPassword;
        this.phone = phone;
        this.occupation = occupation;
    }

    public RegisterDTO() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public String getPhone() {
        return phone;
    }
}
