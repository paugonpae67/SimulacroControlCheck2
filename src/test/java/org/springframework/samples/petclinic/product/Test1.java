package org.springframework.samples.petclinic.product;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.samples.petclinic.grooming.GroomingPackage;
import org.springframework.samples.petclinic.grooming.GroomingPackageRepository;
import org.springframework.samples.petclinic.grooming.GroomingType;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;

@DataJpaTest
public class Test1 extends ReflexiveTest {
    @Autowired(required = false)
    GroomingPackageRepository repo;    
    @Autowired(required = false)
    EntityManager em;    

    @Test
    public void test1(){
        repositoryExists();
        testConstraints();
        testAnnotations();        
    }

    public void repositoryExists() {
        if(repo==null)
            fail("The repository was not injected into the tests, its autowired value was null");
    }

    @Transactional
    public void testConstraints(){        
        
        GroomingPackage d=createValidGroomingPackage(em);

        checkThatFieldsAreMandatory(d,em, "description");

        d.setCost(0);
        checkThatEntityIsNotValid(d, em, "You are not constraining the cost, since the value 0 was considered valid (and it should not be valid)");

        d.setCost(-10);
        checkThatEntityIsNotValid(d, em, "You are not constraining the cost, since the value -10 was considered valid (and it should not be valid)");

        d.setCost(23);
        d.setDescription("");
        checkThatEntityIsNotValid(d, em, "You are not constraining the description, since an empty string was considered valid (and it should not be valid)");

        d.setDescription("test description");
    }

    void testAnnotations(){    
    
    }    

    public static GroomingPackage createValidGroomingPackage(EntityManager em){
        GroomingType t3=em.find(GroomingType.class, 3);
        GroomingType t5=em.find(GroomingType.class,5);
        GroomingType t6=em.find(GroomingType.class,6);
        GroomingPackage d=new GroomingPackage();
        d.setDescription("Test package");
        d.setCost(23.0);
        d.setContents(List.of(t3,t5,t6));
        return d;
    }
}
