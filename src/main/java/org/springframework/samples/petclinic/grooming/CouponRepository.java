package org.springframework.samples.petclinic.grooming;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CouponRepository extends CrudRepository<Coupon, Integer>{
    Coupon save(Coupon o);
    List<Coupon> findAll();
    Optional<Coupon> findById(Integer id);
}
