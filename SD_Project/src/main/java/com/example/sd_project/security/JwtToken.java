package com.example.sd_project.security;

import com.example.sd_project.business.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JwtToken {

    public static String getJwtoken(User user){
        Map<String, Object> claims = new HashMap<String,Object>();
        claims.put("id",user.getId());
        claims.put("name",user.getName());
        claims.put("email",user.getEmail());
        claims.put("password",user.getPassword());
        return Jwts.builder().setHeaderParam("alg","RS256").setHeaderParam("typ","JWT")
                .setClaims(claims)
                .compact();
    }

    public static User getUser(String token){
        try{
            String payload = token.split("\\.")[1];
            String json = new String(Base64.decodeBase64(payload),"UTF-8");
            JSONObject obj = new JSONObject(json);
            String password = obj.getString("password");
            String email = obj.getString("email");
            String name = obj.getString("name");
            long id = obj.getLong("id");
            return new User(id,name, email,password);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }
}
