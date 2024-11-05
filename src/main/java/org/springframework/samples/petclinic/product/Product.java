package org.springframework.samples.petclinic.product;

import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {    
    String name;
    Integer price;
    @Transient
    ProductType type;
}
