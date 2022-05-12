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

    @Autowired
    private StoreService storeService;

    public ArrayList<Product> getProductsByStoreId(Long id){
        return new ArrayList<>(productRepository.getByStore_Id(id));
    }

    public Product addProduct(ProductDTO productDTO, Admin admin) throws Exception{
        if(productDTO.getName()==null || productDTO.getName().equals(""))
            throw new Exception("name required");
        if(productDTO.getPrice() <= 0)
            throw new Exception("invalid price");
        if(productDTO.getCategory()==null || productDTO.getCategory().equals(""))
            throw new Exception("category required");
        if(productDTO.getLink()==null || productDTO.getLink().equals(""))
            throw new Exception("link required");

        Product product = new Product(productDTO.getName(), productDTO.getPrice(), productDTO.getCategory(), productDTO.getLink(), admin.getStore());
        admin.getStore().addProduct(product);

        productRepository.save(product);
        storeService.updateStore(admin.getStore());

        return product;
    }

    public Product updateProduct(ProductDTO productDTO) throws Exception{
        if(productDTO.getName()==null || productDTO.getName().equals(""))
            throw new Exception("name required");
        if(productDTO.getPrice() <= 0)
            throw new Exception("invalid price");
        if(productDTO.getCategory()==null || productDTO.getCategory().equals(""))
            throw new Exception("category required");
        if(productDTO.getLink()==null || productDTO.getLink().equals(""))
            throw new Exception("link required");

        Product product = productRepository.getById(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setCategory(productDTO.getCategory());
        product.setLink(productDTO.getLink());

        productRepository.save(product);

        return product;
    }
}
