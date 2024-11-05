package org.springframework.samples.petclinic.grooming;

import org.springframework.samples.petclinic.model.NamedEntity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class GroomingType extends NamedEntity {
    @NotBlank
    String description;    
}
