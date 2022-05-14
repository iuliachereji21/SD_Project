package com.example.sd_project.business.service;

import com.example.sd_project.business.DTOs.ProductDTO;
import com.example.sd_project.business.model.Admin;
import com.example.sd_project.business.model.Product;
import com.example.sd_project.business.model.Store;
import com.example.sd_project.persistance.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    //Class to be tested
    private ProductService productService;

    //Dependencies
    private ProductRepository productRepository;

    @Before
    public void setup(){
        productService = new ProductService();
        productRepository = mock(ProductRepository.class);
        productService.setProductRepository(productRepository);
    }

    @Test
    public void getProductsByStoreIdTest(){
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());
        products.add(new Product());
        products.add(new Product());

        when(productRepository.getByStore_Id(1L)).thenReturn(products);

        ArrayList<Product> returnedProducts = productService.getProductsByStoreId(1L);
        Assert.assertEquals(products,returnedProducts);
    }

    @Test
    public void addProductTest(){
        Store store = new Store();
        store.setProducts(new ArrayList<>());
        Product product = new Product("title",22,"gresie","aaaa",store);

        Admin admin = new Admin();
        admin.setStore(store);
        ProductDTO productDTO = new ProductDTO(product);

        try {
            Product returnedProduct = productService.addProduct(productDTO,admin);
            Assert.assertEquals(product.getName(),returnedProduct.getName());
        } catch (Exception e) {
            Assert.fail("\"It shuld not have thrown an error");
        }
    }

    @Test
    public void updateProductTest(){
        Store store = new Store();
        store.setProducts(new ArrayList<>());
        store.setId(1L);
        Product product = new Product(11L,"title",22,"gresie","aaaa",store);

        Admin admin = new Admin();
        admin.setStore(store);
        ProductDTO productDTO = new ProductDTO(product);
        productDTO.setName("new title");

        when(productRepository.getById(11L)).thenReturn(product);

        try {
            Product returnedProduct = productService.updateProduct(productDTO,1L);
            Assert.assertEquals(productDTO.getName(),returnedProduct.getName());
        } catch (Exception e) {
            Assert.fail("\"It shuld not have thrown an error");
        }
    }

    @Test
    public void getAllProductsTest(){
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());
        products.add(new Product());
        products.add(new Product());

        when(productRepository.findAll()).thenReturn(products);

        ArrayList<Product> returnedProducts = productService.getAllProducts();
        Assert.assertEquals(products,returnedProducts);
    }

    @Test
    public void getProductByIdTest(){
        Product product = new Product();

        when(productRepository.getById(1L)).thenReturn(product);

        Product returnedProduct = productService.getProductById(1L);
        Assert.assertEquals(product,returnedProduct);
    }
}
