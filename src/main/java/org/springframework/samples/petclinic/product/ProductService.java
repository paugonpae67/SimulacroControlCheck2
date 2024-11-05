package org.springframework.samples.petclinic.product;

import java.util.List;

public class ProductService {
    ProductRepository pr;

    public ProductService(ProductRepository pr){
        this.pr=pr;
    }

    public List<Product> getAllProducts(){
        // TODO: CHANGE TO SOLVE TEST 4!
        return null;
    }

    public Product getProductById(Integer id){
        return null;
    }  

    public void save(Product p) throws UnfeasibleProductUpdate{
        // TODO: CHANGE TO SOLVE TEST 4!        
    }

    public ProductType getProductType(String name){
        // TODO: CHANGE TO SOLVE TEST 5!
        return null;
    }

    public List<Product> getProductsCheaperThan(Integer value){
        // TODO: CHANGE TO SOLVE TEST 6!
        return null;
    }
}
