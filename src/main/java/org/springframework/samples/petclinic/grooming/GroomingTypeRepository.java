package org.springframework.samples.petclinic.grooming;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface GroomingTypeRepository extends CrudRepository <GroomingType, Integer> {
    List<GroomingType> findAll();
    GroomingType findByName(String text);
}
