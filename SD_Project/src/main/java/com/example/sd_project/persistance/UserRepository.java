package com.example.sd_project.persistance;

import com.example.sd_project.business.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Long> {
}
