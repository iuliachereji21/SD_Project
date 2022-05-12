package com.example.sd_project.business.service;

import com.example.sd_project.business.model.Store;
import com.example.sd_project.persistance.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;

    public void addStore(Store store){
        storeRepository.save(store);
    }
}
