package com.example.sd_project.business.service;

import com.example.sd_project.business.DTOs.DesignDTO;
import com.example.sd_project.business.DTOs.ProductDTO;
import com.example.sd_project.business.model.Customer;
import com.example.sd_project.business.model.Design;
import com.example.sd_project.business.model.Product;
import com.example.sd_project.persistance.DesignRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DesignServiceTest {

    //Class to be tested
    private DesignService designService;

    //Dependencies
    private DesignRepository designRepository;
    private ProductService productService;

    @Before
    public void setup(){
        designService = new DesignService();
        designRepository = mock(DesignRepository.class);
        productService = mock(ProductService.class);

        designService.setDesignRepository(designRepository);
        designService.setProductService(productService);
    }

    @Test
    public void addDesignTest(){
        DesignDTO designDTO = new DesignDTO();
        designDTO.setTitle("my title");

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1);
        productDTO.setName("my product");
        productDTO.setPrice("22");
        productDTO.setCategory("gresie");
        productDTO.setLink("aaaaaa");

        Product product = new Product(1,"my product",22,"gresie","aaaaaa",null);
        product.setDesignsItAppearsIn(new ArrayList<>());

        designDTO.setProducts(Arrays.asList(productDTO));

        Customer customer = new Customer(2,"iulia","iulia@yahoo.com","0000", "","student");
        customer.setDesigns(new ArrayList<>());

        when(productService.getProductById(productDTO.getId())).thenReturn(product);

        try{
            Design design = designService.addDesign(designDTO,customer);
            Assert.assertTrue(design.getTitle().equals(designDTO.getTitle()));
            Assert.assertTrue(customer.getDesigns().contains(design));
            Assert.assertTrue(product.getDesignsItAppearsIn().contains(design));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            Assert.assertTrue("It shuld not have thrown an error",false);
        }

    }

    @Test
    public void getAllPublicDesignsTest(){
        ArrayList<Design> designs = new ArrayList<>();
        designs.add(new Design());
        designs.add(new Design());
        designs.add(new Design());
        designs.add(new Design());
        when(designRepository.getAllByIsPublicIsTrue()).thenReturn(designs);

        ArrayList<Design> returnedDesigns = designService.getAllPublicDesigns();
        Assert.assertEquals(designs,returnedDesigns);
    }

    @Test
    public void getDesignsByCustomerIdTest(){
        ArrayList<Design> designs = new ArrayList<>();
        designs.add(new Design());
        designs.add(new Design());
        designs.add(new Design());
        designs.add(new Design());
        when(designRepository.getAllByCustomer_Id(1L)).thenReturn(designs);

        ArrayList<Design> returnedDesigns = designService.getDesignsByCustomerId(1L);
        Assert.assertEquals(designs,returnedDesigns);
    }

    @Test
    public void updateDesignTest(){
        DesignDTO designDTO = new DesignDTO();
        designDTO.setTitle("new title");
        designDTO.setId(1L);

        Design design = new Design();
        design.setTitle("old design title");
        design.setId(1L);
        design.setCustomer(new Customer(2L,"iulia","iulia@yahoo.com","0000", "","student"));

        when(designRepository.getById(1L)).thenReturn(design);

        try{
            Design updatedDesign = designService.updateDesign(designDTO,2L);
            Assert.assertEquals(designDTO.getTitle(),updatedDesign.getTitle());
        }
        catch (Exception e){
            Assert.fail("It shuld not have thrown an error");
        }

    }
}
