package org.springframework.samples.petclinic.grooming;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroomingTypeService {
    

    @Autowired
    GroomingTypeRepository tr;

    @Transactional(readOnly = true)
    public List<GroomingType> getAllGroomingTypes(){
        return tr.findAll();
    }

    @Transactional(readOnly = true)
    public GroomingType getGroomingTypeById(Integer id){
        Optional<GroomingType> t=tr.findById(id);
        return t.isPresent()?t.get():null;
    }

    @Transactional(readOnly = true)
    public GroomingType getGroomingTypeByName(String name){
        return tr.findByName(name);
    }

    @Transactional
    public GroomingType save(GroomingType any) {
        tr.save(any);
        return any;
    }    
}
