package org.springframework.samples.petclinic.product;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.grooming.Coupon;
import org.springframework.samples.petclinic.grooming.CouponService;
import org.springframework.samples.petclinic.grooming.UnfeasibleCouponException;
import org.springframework.samples.petclinic.visit.Visit;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class Test10 {
  

	@Autowired
	CouponService couponService;

	@Autowired
	EntityManager em;

    @Test
	public void test10() {
		couponVisitCreatedOk();
		collidingCouponDetected();
	}

	private void collidingCouponDetected() {
		Visit v = em.find(Visit.class, 2);
		Coupon c = em.find(Coupon.class, 1);

		assertThrows(UnfeasibleCouponException.class, () -> couponService.addVisit(c, v),"The coupon is not valid for the days of the visit, an Unfeasible Coupon exception should be thrown.");
	}

	private void couponVisitCreatedOk() {
		Visit v = em.find(Visit.class, 2);
		Coupon c = em.find(Coupon.class, 2);
		
		try {
			couponService.addVisit(c, v);
		} catch (UnfeasibleCouponException e) {
			fail("The coupon is valid, the exteption shold not be thrown! :"+e.getMessage());
		}
	}
}
