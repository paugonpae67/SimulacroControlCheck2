package org.springframework.samples.petclinic.grooming;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.visit.Visit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Coupon extends BaseEntity{
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name="start")
    LocalDate startDate;
    
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name="finish")
    LocalDate expiryDate;
    
    @NotNull
    @ManyToOne
    GroomingPackage groomingPackage;    

    @NotNull
    @ManyToMany
    List<Visit> consumed;
}

