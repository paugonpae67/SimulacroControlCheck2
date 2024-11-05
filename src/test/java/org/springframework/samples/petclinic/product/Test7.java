package org.springframework.samples.petclinic.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.samples.petclinic.grooming.GroomingPackage;
import org.springframework.samples.petclinic.grooming.GroomingPackageRepository;

@DataJpaTest
public class Test7 {

    @Autowired
    GroomingPackageRepository gr;
    
    @Test
    public void test() {
        validategetPackagesByDatesAndCost();
    }
    // List<Watch> findOverlappedWatches(LocaDate date, LocalTime time, int vetId);

    private void validategetPackagesByDatesAndCost() {
        List<GroomingPackage> packages = gr.findPackagesByDatesAndCost(
            LocalDate.of(2023, 01, 10),
            LocalDate.of(2023, 2, 18),
            60);

        assertNotNull(packages);
        assertEquals(1, packages.size());

        packages = gr.findPackagesByDatesAndCost(
            LocalDate.of(2023, 01, 10),
            LocalDate.of(2023, 1, 20),
            60);

        assertNotNull(packages);
        assertEquals(2, packages.size());

        packages = gr.findPackagesByDatesAndCost(
            LocalDate.of(2023, 06, 10),
            LocalDate.of(2023, 7, 20),
            60);

        assertNotNull(packages);
        assertEquals(0, packages.size());

    }
}
