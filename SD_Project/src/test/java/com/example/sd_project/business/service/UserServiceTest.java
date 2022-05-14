package com.example.sd_project.business.service;

import com.example.sd_project.business.DTOs.LogInDTO;
import com.example.sd_project.business.DTOs.RegisterDTO;
import com.example.sd_project.business.model.User;
import com.example.sd_project.persistance.UserRepository;
import com.example.sd_project.security.PasswordHash;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    //Class to be tested
    private UserService userService;

    //Dependencies
    private UserRepository userRepository;

    @Before
    public void setup(){
        userService = new UserService();
        userRepository = mock(UserRepository.class);
        userService.setUserRepository(userRepository);
    }

    @Test
    public void logInTest(){
        User user = new User(1,"iulia","iulia@yahoo.com", PasswordHash.hashPassword("0000"));
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Arrays.asList(user));
        LogInDTO logInDTO = new LogInDTO("iulia@yahoo.com","0000");

        try{
            User returnedUser = userService.logIn(logInDTO);
            Assert.assertEquals(user,returnedUser);
        }
        catch (Exception e){
            Assert.fail("\"It shuld not have thrown an error");
        }
    }

    @Test
    public void registerTest(){
        RegisterDTO registerDTO = new RegisterDTO("iulia","iulia@gmail.com","0000","0000","","");

        when(userRepository.findByEmail("iulia@gmail.com")).thenReturn(new ArrayList<>());

        try {
            User returnedUser = userService.register(registerDTO);
            Assert.assertEquals(registerDTO.getEmail(),returnedUser.getEmail());
        } catch (Exception e) {
            Assert.fail("\"It shuld not have thrown an error");
        }
    }

    @Test
    public void getUserByEmailTest(){
        User user = new User();
        when(userRepository.findByEmail("iulia@gmail.com")).thenReturn(Arrays.asList(user));

        try {
            User returnedUser = userService.getUserByEmail("iulia@gmail.com");
            Assert.assertEquals(user,returnedUser);
        } catch (Exception e) {
            Assert.fail("\"It shuld not have thrown an error");
        }
    }
}
