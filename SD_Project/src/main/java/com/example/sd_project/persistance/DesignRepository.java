package com.example.sd_project.persistance;

import com.example.sd_project.business.model.Design;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface DesignRepository  extends JpaRepository<Design,Long> {

    ArrayList<Design> getAllByIsPublicIsTrue();

    ArrayList<Design> getAllByCustomer_Id(Long id);

    Design getById(Long id);
}
