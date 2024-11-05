package org.springframework.samples.petclinic.product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository{
    Optional<Product> findById(Integer id);

    List<Product> findAll();

    Product save(Product p);
}
