package com.example.sd_project.business.service;

import com.example.sd_project.business.DTOs.LogInDTO;
import com.example.sd_project.business.DTOs.RegisterDTO;
import com.example.sd_project.business.model.Customer;
import com.example.sd_project.business.model.User;
import com.example.sd_project.persistance.UserRepository;
import com.example.sd_project.security.PasswordHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    public User logIn(LogInDTO logInDTO) throws Exception{
        if(logInDTO.getEmail()==null || logInDTO.getEmail().equals(""))
            throw new Exception("email required");

        if(logInDTO.getPassword()==null || logInDTO.getPassword().equals(""))
            throw new Exception("password required");

        String hashedPassword = PasswordHash.hashPassword(logInDTO.getPassword());

        ArrayList<User> users = new ArrayList<>(userRepository.findByEmail(logInDTO.getEmail()));
        if(users.size()==0){
            logger.debug("The email "+logInDTO.getEmail() +" was not found in the database for log in");
            throw new Exception("bad credentials");
        }
        else{
            if(!users.get(0).getPassword().equals(hashedPassword)){
                logger.debug("The password introduced for user with id "+users.get(0).getId()+" is incorrect");
                throw new Exception("bad credentials");
            }
            else {
                logger.info("User with id "+users.get(0).getId()+" has logged in");
                return users.get(0);
            }
        }
    }

    public User register(RegisterDTO registerDTO) throws Exception {
        if(registerDTO.getEmail()==null || registerDTO.getEmail().equals(""))
            throw new Exception("email required");

        if(registerDTO.getName()==null || registerDTO.getName().equals(""))
            throw new Exception("name required");

        if(registerDTO.getPassword()==null || registerDTO.getPassword().equals(""))
            throw new Exception("password required");

        if(registerDTO.getRepeatedPassword()==null || registerDTO.getRepeatedPassword().equals(""))
            throw new Exception("repeated password required");

        if(!registerDTO.getRepeatedPassword().equals(registerDTO.getPassword()))
            throw new Exception("passwords don't match");

        if(registerDTO.getPhone()!=null && !Objects.equals(registerDTO.getPhone(), "")){
            String regexPattern = "^\\d{10}$";
            if(!Pattern.compile(regexPattern).matcher(registerDTO.getPhone()).matches())
                throw new Exception("invalid phone number");
        }

        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if(!Pattern.compile(regexPattern).matcher(registerDTO.getEmail()).matches())
            throw new Exception("invalid email");

        ArrayList<User> users = new ArrayList<>(userRepository.findByEmail(registerDTO.getEmail()));
        if(users.size()!=0){
            logger.debug("The email "+registerDTO.getEmail()
                    +" belonging to an existing account was inserted to create a new account");
            throw new Exception("email already exists");
        }
        else{
            Customer newCustomer = (Customer) Factory.createUser("Customer");
            newCustomer.setEmail(registerDTO.getEmail());
            newCustomer.setName(registerDTO.getName());
            newCustomer.setPhone(registerDTO.getPhone());
            newCustomer.setOccupation(registerDTO.getOccupation());
            newCustomer.setDesigns(new ArrayList<>());
            newCustomer.setPassword(PasswordHash.hashPassword(registerDTO.getPassword()));

            userRepository.save(newCustomer);
            logger.info("A new customer account with id "+newCustomer.getId() + " was created");

            return newCustomer;
        }
    }

    public User getUserByEmail(String email){
        ArrayList<User> users = new ArrayList<>(userRepository.findByEmail(email));
        return users.get(0);
    }
}
