package com.example.sd_project.presentation;

import com.example.sd_project.business.service.DesignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DesignController {

    @Autowired
    private DesignService designService;
}
