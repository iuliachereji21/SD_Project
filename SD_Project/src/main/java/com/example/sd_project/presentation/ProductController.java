package com.example.sd_project.presentation;

import com.example.sd_project.business.DTOs.ProductDTO;
import com.example.sd_project.business.DTOs.ResponseDTO;
import com.example.sd_project.business.DTOs.UserDTO;
import com.example.sd_project.business.model.Admin;
import com.example.sd_project.business.model.Product;
import com.example.sd_project.business.model.Store;
import com.example.sd_project.business.model.User;
import com.example.sd_project.business.service.ProductService;
import com.example.sd_project.business.service.UserService;
import com.example.sd_project.security.JwtToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;


    private Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/admin")
    public ResponseEntity getProductsByAdmin(@RequestHeader String token){

        User user = JwtToken.getUser(token);
        if(user == null){
            logger.warn("An unauthorized access was atempted at endpoint: "+ "/admin");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        }

        User newuser = userService.getUserByEmail(user.getEmail());

        if(newuser == null){
            logger.warn("An unauthorized access was atempted at endpoint: "+ "/admin");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        }

        if(!(newuser instanceof Admin)){
            logger.warn("An unauthorized access was atempted at endpoint: "+ "/admin");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        }

        ArrayList<Product> productsList = productService.getProductsByStoreId(((Admin) newuser).getStore().getId());
        ArrayList<ProductDTO> products = new ArrayList<>();
        for(int i=0;i<productsList.size();i++){
            products.add(new ProductDTO(productsList.get(i)));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(products);
    }

    @PostMapping("/admin")
    public ResponseEntity addProduct(@RequestHeader String token, @RequestBody ProductDTO productDTO){

        User user = JwtToken.getUser(token);
        if(user == null){
            logger.warn("An unauthorized access was atempted at endpoint: "+ "/admin");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        }

        User newuser = userService.getUserByEmail(user.getEmail());

        if(newuser == null){
            logger.warn("An unauthorized access was atempted at endpoint: "+ "/admin");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        }

        if(!(newuser instanceof Admin)){
            logger.warn("An unauthorized access was atempted at endpoint: "+ "/admin");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        }

        try{
            Product product = productService.addProduct(productDTO, (Admin) newuser);

            logger.info("A new product with id "+product.getId() + " was created");
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ProductDTO(product));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(e.getMessage()));
        }
    }

    @PatchMapping("/admin")
    public ResponseEntity updateProduct(@RequestHeader String token, @RequestBody ProductDTO productDTO){

        User user = JwtToken.getUser(token);
        if(user == null){
            logger.warn("An unauthorized access was atempted at endpoint: "+ "/admin");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        }

        User newuser = userService.getUserByEmail(user.getEmail());

        if(newuser == null){
            logger.warn("An unauthorized access was atempted at endpoint: "+ "/admin");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        }

        if(!(newuser instanceof Admin)){
            logger.warn("An unauthorized access was atempted at endpoint: "+ "/admin");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ResponseDTO("unauthorized"));
        }

        try{
            Product product = productService.updateProduct(productDTO);

            logger.info("The product with id "+product.getId() + " was updated");
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ProductDTO(product));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDTO(e.getMessage()));
        }

    }
}
