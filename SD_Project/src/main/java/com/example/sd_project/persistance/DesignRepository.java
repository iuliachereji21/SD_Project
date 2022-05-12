package com.example.sd_project.persistance;

import com.example.sd_project.business.model.Design;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignRepository  extends JpaRepository<Design,Long> {
}
