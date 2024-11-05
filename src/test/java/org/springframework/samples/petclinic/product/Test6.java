package org.springframework.samples.petclinic.product;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.grooming.Coupon;
import org.springframework.samples.petclinic.grooming.CouponRepository;
import org.springframework.samples.petclinic.grooming.CouponService;
import org.springframework.samples.petclinic.grooming.GroomingPackage;
import org.springframework.samples.petclinic.grooming.GroomingPackageRepository;
import org.springframework.samples.petclinic.grooming.GroomingPackageService;
import org.springframework.samples.petclinic.grooming.GroomingType;
import org.springframework.samples.petclinic.grooming.UnfeasibleCouponException;

@ExtendWith(MockitoExtension.class)
public class Test6 extends ReflexiveTest{
     @Mock
    GroomingPackageRepository or;
    @Mock
    CouponRepository dr;

    
    GroomingPackageService os;    
    CouponService ds;
    
    @BeforeEach
    public void configuation(){
        os=new GroomingPackageService(or);
        ds=new CouponService(dr);
    }

    @Test 
    public void test6(){
        groomingPackageServiceIsInjected();
        groomingPackageServiceCanGetPackages();        
        groomingPackageServiceCanSaveOffers();
        couponServiceIsInjected();
        couponServiceCanGetDiscounts();        
        couponServiceCanSaveCoupons();
        checkTransactionalityOfGroomingPackageService();
        checkTransactionalityOfCouponService();
        
    }

    public void checkTransactionalityOfGroomingPackageService(){
        checkTransactional(GroomingPackageService.class,"save", GroomingPackage.class);        
        checkTransactional(GroomingPackageService.class,"getAll");
    }

    public void checkTransactionalityOfCouponService(){
        checkTransactional(CouponService.class,"save", Coupon.class);        
        checkTransactional(CouponService.class,"getAll");
    }
    
    public void groomingPackageServiceIsInjected() {
        assertNotNull(os,"GroomingPackageService was not injected by spring");       
    }
    
    public void groomingPackageServiceCanGetPackages(){
        assertNotNull(os,"GroomingPackageService was not injected by spring");
        when(or.findAll()).thenReturn(List.of());
        List<GroomingPackage> gp=os.getAll();
        assertNotNull(gp,"The list of GroomingPackage found by the GroomingPackageService was null");
        // The test fails if the service does not invoke the findAll of the repository:
        verify(or).findAll();            
    }

    public void couponServiceIsInjected(){
        assertNotNull(ds,"CouponService was not injected by spring");       
    }

    public void couponServiceCanGetDiscounts(){
        assertNotNull(os,"CouponService was not injected by spring");
        when(dr.findAll()).thenReturn(List.of());
        List<Coupon> discounts=ds.getAll();
        assertNotNull(discounts,"The list of coupons found by the CouponService was null");
        // The test fails if the service does not invoke the findAll of the repository:
        verify(dr).findAll();               
    }

    public void couponServiceCanSaveCoupons(){
        assertNotNull(ds,"CouponService was not injected by spring");
        when(dr.save(any(Coupon.class))).thenReturn(null);
        Coupon s=new Coupon();
        s.setConsumed(List.of());
        s.setStartDate(LocalDate.of(2023,01,05));
        s.setGroomingPackage(new GroomingPackage());
        s.setExpiryDate(LocalDate.of(2023,02,21));
        ds.save(s);
        // The test fails if the service does not invoke the save function of the repository:
        verify(dr).save(s);     
    }


    

    private void groomingPackageServiceCanSaveOffers() {        
        assertNotNull(os,"GroomingPackageService was not injected by spring");
        when(or.save(any(GroomingPackage.class))).thenReturn(null);
        GroomingPackage o=new GroomingPackage();
        o.setContents(List.of(new GroomingType()));
        o.setCost(23);
        o.setDescription("bla");
        // try {
            os.save(o);
        // } catch (UnfeasibleOfferException e) {
        //     fail("The exception should not be thrown.");
        // }
        // The test fails if the service does not invoke the save function of the repository:
        verify(or).save(o);                
    }

}
