package com.example.sd_project.presentation;

import com.example.sd_project.business.DTOs.LogInDTO;
import com.example.sd_project.business.DTOs.ResponseDTO;
import com.example.sd_project.business.DTOs.UserDTO;
import com.example.sd_project.business.DTOs.RegisterDTO;
import com.example.sd_project.business.model.Customer;
import com.example.sd_project.business.model.User;
import com.example.sd_project.business.service.UserService;
import com.example.sd_project.security.JwtToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping( "/login")
    public ResponseEntity logIn(@RequestBody LogInDTO logInDTO){
        try{
            User user = userService.logIn(logInDTO);
            String token = JwtToken.getJwtoken(user);
            logger.debug("The token "+token+" was given at the log in to the user with id "+user.getId());

            if(user instanceof Customer){
                logger.info("Customer with id "+user.getId()+" has logged in");
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new UserDTO(user.getId(), true,token));
            }
            else{
                logger.info("Admin with id "+user.getId()+" has logged in");
                return ResponseEntity.status(HttpStatus.OK)
                        .body(new UserDTO(user.getId(), false,token));
            }

        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(e.getMessage()));
        }
    }

    @PostMapping( "/register")
    public ResponseEntity registerUser(@RequestBody RegisterDTO registerDTO){
        try{
            Customer customer = (Customer) userService.register(registerDTO);

            String token = JwtToken.getJwtoken(customer);
            logger.debug("The token "+token+" was created for the new user with id "+customer.getId());

            logger.info("A new customer account with id "+customer.getId() + " was created");
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new UserDTO(customer.getId(),true,token));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(e.getMessage()));
        }
    }
}
