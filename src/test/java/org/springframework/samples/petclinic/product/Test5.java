package org.springframework.samples.petclinic.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.samples.petclinic.grooming.Coupon;
import org.springframework.samples.petclinic.grooming.GroomingPackage;

import jakarta.persistence.EntityManager;

@DataJpaTest
public class Test5 extends ReflexiveTest {
    
    @Autowired
    EntityManager em;

    @Test
    public void testLinks() {
        checkLinkedById(Coupon.class,1,"getGroomingPackage",1,em);            
        checkLinkedById(Coupon.class,2,"getGroomingPackage",2,em);            
        checkContainsById(Coupon.class,1,"getConsumed",1,em);
        checkContainsById(Coupon.class,2,"getConsumed",2,em);
        checkContainsById(Coupon.class,2,"getConsumed",3,em);
        checkContainsById(GroomingPackage.class,1,"getContents",1,em);
        checkContainsById(GroomingPackage.class,1,"getContents",2,em);
        checkContainsById(GroomingPackage.class,2,"getContents",3,em);
        checkContainsById(GroomingPackage.class,2,"getContents",5,em);
    }
}
