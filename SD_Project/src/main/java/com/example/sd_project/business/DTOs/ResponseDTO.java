package com.example.sd_project.business.DTOs;

public class ResponseDTO {
    private String message;

    public ResponseDTO(String message){
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
