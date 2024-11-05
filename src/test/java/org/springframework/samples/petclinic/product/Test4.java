package org.springframework.samples.petclinic.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import org.springframework.samples.petclinic.grooming.GroomingPackageRepository;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class Test4 extends ReflexiveTest {
    @Autowired(required = false)
    GroomingPackageRepository gr;
    @Autowired(required = false)
    CouponRepository cr;
    @Autowired
    EntityManager em;


    


    @Test
    public void test4(){
        testInitialPackages();
        testInitialCoupons();
    }



    public void testInitialPackages(){
        List<GroomingPackage> offers=gr.findAll();
        assertTrue(offers.size()==2, "Exactly two Grooming Packages should be present in the DB");
        
        Optional<GroomingPackage> o1=gr.findById(1);
        assertTrue(o1.isPresent(),"There should exist a GroomingPackage with id:1");
        assertEquals(50,o1.get().getCost());        
        assertEquals("Hair cut + Nails",o1.get().getDescription());        

        Optional<GroomingPackage> o2=gr.findById(2); 
        assertTrue(o2.isPresent(),"There should exist a GroomingPackage with id:2");       
        assertEquals(30,o2.get().getCost());
        assertEquals("Bath + Legs massage",o2.get().getDescription());        

        

    }

    public void testInitialCoupons()
    {
        List<Coupon> coupons = cr.findAll();        
        assertTrue(coupons.size()==2,"Exactly two coupons should be present in the DB");
        Integer id=null;
        for(Coupon v:coupons) {
            id=(Integer)invokeMethodReflexively(v, "getId");
            if(id.equals(1)){
                assertEquals(LocalDate.of(2023,01,05),v.getStartDate(), "incorrect starting date for id:1");
                assertEquals(LocalDate.of(2023,02,21),v.getExpiryDate(), "incorrect expiry date for id:1");
            }else if(id.equals(2)){
                assertEquals(LocalDate.of(2022,12,20),v.getStartDate(), "incorrect starting date for id:2");
                assertEquals(LocalDate.of(2023,01,31),v.getExpiryDate(), "incorrect end date for id:2");
            }else {
                fail("The id of the coupon should be either 1 or 2");
            }
        }
    }
}
