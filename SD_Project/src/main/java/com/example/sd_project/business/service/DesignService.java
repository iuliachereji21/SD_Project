package com.example.sd_project.business.service;

import com.example.sd_project.persistance.DesignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DesignService {

    @Autowired
    private DesignRepository designRepository;
}
