package com.example.sd_project.presentation;

import com.example.sd_project.business.DTOs.DesignDTO;
import com.example.sd_project.business.DTOs.ResponseDTO;
import com.example.sd_project.business.model.*;
import com.example.sd_project.business.service.DesignService;
import com.example.sd_project.business.service.UserService;
import com.example.sd_project.security.JwtToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@RestController
public class DesignController {

    @Autowired
    private DesignService designService;

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(DesignController.class);

    @PostMapping("/products")
    public ResponseEntity addDesign(@RequestHeader String token, @RequestBody DesignDTO designDTO){

        User user = JwtToken.getUser(token);
        if(user == null){
            logger.warn("An unauthorized access was atempted at endpoint: "+ "/products");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        }

        User newuser = userService.getUserByEmail(user.getEmail());

        if(newuser == null){
            logger.warn("An unauthorized access was atempted at endpoint: "+ "/products");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        }

        if(!(newuser instanceof Customer)){
            logger.warn("An unauthorized access was atempted at endpoint: "+ "/products");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        }

        try{
            Design design = designService.addDesign(designDTO, (Customer) newuser);

            logger.info("A new design with id "+design.getId() + " was created");
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new DesignDTO(design));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(e.getMessage()));
        }
    }

    @GetMapping("/designs")
    public ResponseEntity getAllPublicDesigns(){
        ArrayList<Design> designsList = designService.getAllPublicDesigns();
        ArrayList<DesignDTO> designs = new ArrayList<>();
        for(int i=0;i<designsList.size();i++){
            designs.add(new DesignDTO(designsList.get(i)));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(designs);
    }

    @GetMapping("/myDesigns")
    public ResponseEntity getDesignsByCustomer(@RequestHeader String token){
        User user = JwtToken.getUser(token);
        if(user == null){
            logger.warn("An unauthorized access was atempted at endpoint: "+ "/myDesigns");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        }

        User newuser = userService.getUserByEmail(user.getEmail());

        if(newuser == null){
            logger.warn("An unauthorized access was atempted at endpoint: "+ "/myDesigns");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        }

        if(!(newuser instanceof Customer)){
            logger.warn("An unauthorized access was atempted at endpoint: "+ "/myDesigns");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        }

        ArrayList<Design> designsList = designService.getDesignsByCustomerId(newuser.getId());
        ArrayList<DesignDTO> designs = new ArrayList<>();
        for(int i=0;i<designsList.size();i++){
            designs.add(new DesignDTO(designsList.get(i)));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(designs);
    }

    @PatchMapping("/myDesigns")
    public ResponseEntity updateDesign(@RequestHeader String token, @RequestBody DesignDTO designDTO){
        User user = JwtToken.getUser(token);
        if(user == null){
            logger.warn("An unauthorized access was atempted at endpoint: "+ "/myDesigns");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        }

        User newuser = userService.getUserByEmail(user.getEmail());

        if(newuser == null){
            logger.warn("An unauthorized access was atempted at endpoint: "+ "/myDesigns");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        }

        if(!(newuser instanceof Customer)){
            logger.warn("An unauthorized access was atempted at endpoint: "+ "/myDesigns");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        }

        try{
            Design design = designService.updateDesign(designDTO, newuser.getId());

            logger.info("The design with id "+design.getId() + " was updated");
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new DesignDTO(design));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(e.getMessage()));
        }
    }

    @PostMapping("/designs/pdf")
    public void exportMenuToPdf(@RequestBody DesignDTO designDTO, HttpServletResponse response){
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Shopping List "+designDTO.getTitle()+".pdf";
        response.setHeader(headerKey,headerValue);
        designService.createPdfOfDesignShoppingList(designDTO, response);
    }
}
