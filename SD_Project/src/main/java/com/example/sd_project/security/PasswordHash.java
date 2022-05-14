package com.example.sd_project.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHash {

    public static String hashPassword(String password){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(password.getBytes());
            byte[] resultByteArray = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder();

            for(byte b: resultByteArray){
                stringBuilder.append(String.format("%02x",b));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
