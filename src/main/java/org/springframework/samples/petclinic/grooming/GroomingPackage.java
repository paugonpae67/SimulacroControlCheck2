package org.springframework.samples.petclinic.grooming;

import java.util.List;

import org.springframework.samples.petclinic.model.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class GroomingPackage extends BaseEntity {
    @NotNull
    @Min(1)
    double cost;
    @NotNull
    @NotEmpty
    String description;

    @NotNull
    @ManyToMany
    @NotEmpty
    List<GroomingType> contents;
}
