package com.example.sd_project.business.service;

import com.example.sd_project.business.DTOs.DesignDTO;
import com.example.sd_project.business.DTOs.ProductDTO;
import com.example.sd_project.business.model.Customer;
import com.example.sd_project.business.model.Design;
import com.example.sd_project.business.model.Product;
import com.example.sd_project.persistance.DesignRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Service
public class DesignService {

    @Autowired
    private DesignRepository designRepository;

    @Autowired
    private ProductService productService;

    private Logger logger = LoggerFactory.getLogger(DesignService.class);

    /**
     * Adds a new design to the database
     * @param designDTO the object containing the user's input
     * @param customer the customer that wants to create the design
     * @return the created design if all data is valid
     * @throws Exception if missing or invalid data
     */
    public Design addDesign(DesignDTO designDTO, Customer customer) throws Exception{
        if(designDTO.getTitle()==null || designDTO.getTitle().equals(""))
            throw new Exception("title required");

        ArrayList<Product> products = new ArrayList<>();
        for(ProductDTO productDTO : designDTO.getProducts()){
            products.add(productService.getProductById(productDTO.getId()));
        }

        Design design = new Design(designDTO.getTitle(), designDTO.getIsPublic(), designDTO.getDateAndTime(), customer);
        design.setProducts(products);
        for(Product product : products){
            product.addDesign(design);
        }
        customer.addDesign(design);
        designRepository.save(design);
        logger.info("A new design with id "+design.getId() + " was added by the customer with id "+customer.getId());

        return design;
    }

    /**
     * @return a list of all the designs in the database that are public
     */
    public ArrayList<Design> getAllPublicDesigns(){ return designRepository.getAllByIsPublicIsTrue();}

    /**
     * Searches the database for designs owned by a specific customer
     * @param id the id of the customer
     * @return a list of all the found designs
     */
    public ArrayList<Design> getDesignsByCustomerId(Long id){ return designRepository.getAllByCustomer_Id(id);}

    /**
     * Updates a design
     * @param designDTO the object containing the new data
     * @param customerId the id of the customer who wants to update his design (used for authorization)
     * @return the new updated design
     * @throws Exception if data is missing or invalid
     */
    public Design updateDesign(DesignDTO designDTO, Long customerId) throws Exception{
        if(designDTO.getTitle()==null || designDTO.getTitle().equals(""))
            throw new Exception("title required");

        Design design = designRepository.getById(designDTO.getId());

        if(design.getCustomer().getId() != customerId)
            throw new Exception("unauthorized");

        design.setTitle(designDTO.getTitle());
        design.setIsPublic(designDTO.getIsPublic());

        designRepository.save(design);
        logger.info("The design with id "+design.getId() + " was updated by the customer with id "+customerId);

        return design;
    }

    /**
     * Creates a pdf document containing shopping list for a design
     * @param designDTO the object containing the design informations
     * @param response the http response containing the pdf
     */
    public void createPdfOfDesignShoppingList(DesignDTO designDTO, HttpServletResponse response){
        ArrayList<Product> products = new ArrayList<>();
        for(ProductDTO productDTO : designDTO.getProducts()){
            products.add(productService.getProductById(productDTO.getId()));
        }
        Document document = new Document(PageSize.A4);

        try{
            PdfWriter.getInstance(document, response.getOutputStream());

            document.open();

            Font fontTitle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
            fontTitle.setSize(22);
            fontTitle.setStyle(Font.BOLD);
            Paragraph title = new Paragraph(designDTO.getTitle()+"\n"+"Shopping list"+"\n\n",fontTitle);
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);

            Font fontTitleProduct = FontFactory.getFont(FontFactory.TIMES_ROMAN);
            fontTitleProduct.setSize(16);
            fontTitleProduct.setStyle(Font.BOLD);
            Font fontProdutInfo = FontFactory.getFont(FontFactory.TIMES_ROMAN);
            fontProdutInfo.setSize(12);

            float totalPrice=0;

            for(Product product : products){
                Paragraph titleProduct = new Paragraph(product.getName()+"\n",fontTitleProduct);
                titleProduct.setAlignment(Paragraph.ALIGN_LEFT);
                document.add(titleProduct);

                Paragraph productInfo = new Paragraph(
                        product.getLink()+ "\n"
                                +product.getPrice()+"\n"
                                +product.getStore()+"\n"
                                +product.getStore().getAddress() + "\n");
                productInfo.setAlignment(Paragraph.ALIGN_LEFT);
                document.add(productInfo);
                totalPrice+=product.getPrice();

            }

            Paragraph totalPriceParagraph = new Paragraph("Total price: "+totalPrice,fontTitle);
            totalPriceParagraph.setAlignment(Paragraph.ALIGN_RIGHT);
            document.add(totalPriceParagraph);

            document.close();
        }
        catch (Exception e){}
    }

    public void setDesignRepository(DesignRepository designRepository) {
        this.designRepository = designRepository;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
