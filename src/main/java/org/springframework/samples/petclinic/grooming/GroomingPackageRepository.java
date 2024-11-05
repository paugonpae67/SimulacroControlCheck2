package org.springframework.samples.petclinic.grooming;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GroomingPackageRepository extends CrudRepository<GroomingPackage, Integer> {
    public GroomingPackage save(GroomingPackage o);
    public List<GroomingPackage> findAll();
    public Optional<GroomingPackage> findById(Integer id);

    @Query("SELECT c.groomingPackage FROM Coupon c WHERE c.startDate<=:start AND c.expiryDate>=:end AND c.groomingPackage.cost<:cost") 
    public List<GroomingPackage> findPackagesByDatesAndCost(LocalDate start,  LocalDate end, double cost);
}
