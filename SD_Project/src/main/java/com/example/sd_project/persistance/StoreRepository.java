package com.example.sd_project.persistance;

import com.example.sd_project.business.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository  extends JpaRepository<Store,Long> {
}
