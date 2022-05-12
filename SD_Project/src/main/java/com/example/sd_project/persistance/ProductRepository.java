package com.example.sd_project.persistance;

import com.example.sd_project.business.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository  extends JpaRepository<Product,Long> {
}
