package org.springframework.samples.petclinic.product;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.grooming.Coupon;
import org.springframework.samples.petclinic.grooming.CouponRepository;
import org.springframework.samples.petclinic.grooming.GroomingPackage;
import org.springframework.samples.petclinic.grooming.GroomingType;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class Test2 extends ReflexiveTest {

    @Autowired(required = false)
    CouponRepository repo;
    
    @Autowired
    EntityManager em;

    

    @Test
    public void test2(){
        repositoryExists();
        repositoryContainsMethod();
        testConstraints();
        testAnnotations();
    }


    public void repositoryExists(){
        assertNotNull(repo,"The repository was not injected into the tests, its autowired value was null");
    }


    public void repositoryContainsMethod(){
        if(repo!=null){
            Object v=repo.findById(12);
            assertFalse(null!=v && ((Optional)v).isPresent(), "No result (null) should be returned for a coupon that does not exist");
        }else
            fail("The repository was not injected into the tests, its autowired value was null");
    }

    void testConstraints(){
        // Map<String,List<Object>> invalidValues=Map.of(
        //                                     "percentage",     List.of(
        //                                             0,-1,120                         )                                            
        //                                     );


        Coupon o=createValidCoupon(em);
        em.persist(o);
        
        checkThatFieldsAreMandatory(o, em, "startDate","expiryDate");        
        
        // checkThatValuesAreNotValid(o, invalidValues,em);                
    }

    void testAnnotations(){
        checkThatFieldIsAnnotatedWithDateTimeFormat(Coupon.class,"startDate","dd/MM/yyyy");
        checkThatFieldIsAnnotatedWithDateTimeFormat(Coupon.class,"expiryDate","dd/MM/yyyy");
    }

    public static Coupon createValidCoupon(EntityManager em){
        GroomingPackage gp = Test1.createValidGroomingPackage(em);                
        em.persist(gp);
        
        Coupon c = new Coupon();
        c.setStartDate(LocalDate.of(2021,12,20));
        c.setExpiryDate(LocalDate.of(2021,12,31));
        c.setGroomingPackage(gp);
        c.setConsumed(List.of());

        return c;
    }
}
