package com.example.sd_project.business.service;

import com.example.sd_project.business.DTOs.ProductDTO;
import com.example.sd_project.business.model.Admin;
import com.example.sd_project.business.model.Product;
import com.example.sd_project.persistance.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private Logger logger = LoggerFactory.getLogger(ProductService.class);

    /**
     * Searches the database for products belonging to a specific store
     * @param id the id of the store
     * @return the list of products
     */
    public ArrayList<Product> getProductsByStoreId(Long id){
        return new ArrayList<>(productRepository.getByStore_Id(id));
    }

    /**
     * Adds a new product to the database if data is correct
     * @param productDTO the object containing the user's inputs
     * @param admin the admin that wanted to add the product (used to find the store that he works for)
     * @return the created product
     * @throws Exception if invalid or missing data
     */
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
        logger.info("A new product with id "+product.getId() + " was added by the admin with id "+admin.getId());

        return product;
    }

    /**
     * Updates a product
     * @param productDTO the object containing the new data
     * @param storeId the id of the store that the product belongs to (used for authorization after the product is
     *                found in the database
     * @return the new updated product
     * @throws Exception if data is missing or is invalid
     */
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
        logger.info("The product with id "+product.getId() + " was updated");

        return product;
    }

    /**
     * @return a list of all the products in the database
     */
    public ArrayList<Product> getAllProducts(){
        return productRepository.findAll();
    }

    /**
     * Searches for a product by id
     * @param id the id to be searched by
     * @return the found product
     */
    public Product getProductById(Long id){
        return productRepository.getById(id);
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
