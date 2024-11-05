package org.springframework.samples.petclinic.product;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.grooming.Coupon;
import org.springframework.samples.petclinic.grooming.GroomingPackage;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class Test3 extends ReflexiveTest{
    
    @Autowired(required = false)
    EntityManager em; 

    @Test
    public void test3(){
        testConstraints();
        testAnnotations();        
    }

    private void testAnnotations() {
        checkThatFieldIsAnnotatedWith(GroomingPackage.class, "contents", ManyToMany.class);    
        checkThatFieldIsAnnotatedWith(Coupon.class, "groomingPackage", ManyToOne.class);        
        checkThatFieldIsAnnotatedWith(Coupon.class, "consumed", ManyToMany.class);                
    }

    private void testConstraints() {
        GroomingPackage d=Test1.createValidGroomingPackage(em);
        d.setContents(List.of());
        checkThatEntityIsNotValid(d, em, "You are not constraining the contents, since an empty list was considered valid (and it should not be valid)");
        d.setContents(null);
        checkThatEntityIsNotValid(d, em, "You are not constraining the contents, since null value was considered valid (and it should not be valid)");

        Coupon c=Test2.createValidCoupon(em);
        c.setConsumed(null);
        checkThatEntityIsNotValid(c, em, "You are not constraining the consumed, since null value was considered valid (and it should not be valid)");
        c.setConsumed(List.of());
        checkThatFieldIsMandatory(c, "groomingPackage",GroomingPackage.class,em);       
    }
}
