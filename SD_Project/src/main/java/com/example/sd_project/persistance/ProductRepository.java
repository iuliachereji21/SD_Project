package com.example.sd_project.persistance;

import com.example.sd_project.business.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface ProductRepository  extends JpaRepository<Product,Long> {

    List<Product> getByStore_Id(long id);

    Product getById(Long id);

    ArrayList<Product> findAll();
}
