package com.example.sd_project.business.service;

import com.example.sd_project.business.DTOs.ProductDTO;
import com.example.sd_project.business.model.Admin;
import com.example.sd_project.business.model.Product;
import com.example.sd_project.persistance.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ArrayList<Product> getProductsByStoreId(Long id){
        return new ArrayList<>(productRepository.getByStore_Id(id));
    }

    public Product addProduct(ProductDTO productDTO, Admin admin) throws Exception{
        if(productDTO.getName()==null || productDTO.getName().equals(""))
            throw new Exception("name required");
        if(productDTO.getPrice()==null || productDTO.getPrice().equals(""))
            throw new Exception("invalid price");
        if(productDTO.getCategory()==null || productDTO.getCategory().equals(""))
            throw new Exception("category required");
        if(productDTO.getLink()==null || productDTO.getLink().equals(""))
            throw new Exception("link required");

        Float price;
        try{
            price = Float.parseFloat(productDTO.getPrice());
            if(price<=0)
                throw new Exception("invalid price");
        }
        catch (Exception e){
            throw new Exception("invalid price");
        }

        Product product = new Product(productDTO.getName(),
                                      price,
                                      productDTO.getCategory(),
                                      productDTO.getLink(),
                                      admin.getStore());
        admin.getStore().addProduct(product);

        productRepository.save(product);

        return product;
    }

    public Product updateProduct(ProductDTO productDTO, Long storeId) throws Exception{
        if(productDTO.getName()==null || productDTO.getName().equals(""))
            throw new Exception("name required");
        if(productDTO.getPrice()==null || productDTO.getPrice().equals(""))
            throw new Exception("invalid price");
        if(productDTO.getCategory()==null || productDTO.getCategory().equals(""))
            throw new Exception("category required");
        if(productDTO.getLink()==null || productDTO.getLink().equals(""))
            throw new Exception("link required");

        Product product = productRepository.getById(productDTO.getId());

        Float price;
        try{
            price = Float.parseFloat(productDTO.getPrice());
            if(price<=0)
                throw new Exception("invalid price");
        }
        catch (Exception e){
            throw new Exception("invalid price");
        }

        if(product.getStore().getId() != storeId)
            throw new Exception("unauthorized");

        product.setName(productDTO.getName());
        product.setPrice(price);
        product.setCategory(productDTO.getCategory());
        product.setLink(productDTO.getLink());

        productRepository.save(product);

        return product;
    }

    public ArrayList<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.getById(id);
    }
}
