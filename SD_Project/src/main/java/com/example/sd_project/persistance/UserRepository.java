package com.example.sd_project.persistance;

import com.example.sd_project.business.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository  extends JpaRepository<User,Long> {

    List<User> findByEmail(String email);



    User getById(Long id);
}
