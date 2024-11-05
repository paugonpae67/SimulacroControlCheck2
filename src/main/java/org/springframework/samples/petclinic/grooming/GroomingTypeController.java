package org.springframework.samples.petclinic.grooming;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.samples.petclinic.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/groomingtypes")
public class GroomingTypeController {
    @Autowired
    GroomingTypeService gts;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GroomingType> getAllGroomingTypes(){
        return gts.getAllGroomingTypes();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GroomingType getGroomingType(@PathVariable("id") Integer id){
        GroomingType g= gts.getGroomingTypeById(id);
        if(g==null){
            throw new ResourceNotFoundException("No se encontro");
        }
        return g;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GroomingType createGroomingType(@RequestBody @Valid GroomingType g){
        gts.save(g);
        return g;
    }
    
}
