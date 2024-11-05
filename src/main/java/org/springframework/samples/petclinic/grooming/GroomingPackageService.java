package org.springframework.samples.petclinic.grooming;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class GroomingPackageService {
    GroomingPackageRepository gr;
   
    @Autowired
    public GroomingPackageService(GroomingPackageRepository gr){
        this.gr=gr;
    }

    @Transactional()
    public GroomingPackage save(GroomingPackage o) {
       return gr.save(o);
    }
    
    @Transactional(readOnly = true)
    public List<GroomingPackage> getAll() {
       
        return gr.findAll();
    }
    
}
