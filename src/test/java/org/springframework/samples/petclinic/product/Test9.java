package org.springframework.samples.petclinic.product;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.grooming.GroomingType;
import org.springframework.samples.petclinic.grooming.GroomingTypeController;
import org.springframework.samples.petclinic.grooming.GroomingTypeService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(value = GroomingTypeController.class,		
		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
		excludeAutoConfiguration= SecurityConfiguration.class)
public class Test9 {
    @MockBean
    GroomingTypeService ts;
    
    @Autowired
    private MockMvc mockMvc;

    public static String A_GT_NAME ="New GroomingType name";
    public static String A_GT_DESCRIPTION ="New GroomingType description";


    @Test
    @WithMockUser(value = "spring", authorities = {"admin"})    
    public void test9() throws Exception{  
        testGroomingTypeCreationControllerOK();
        testGroomingTypeCreationControllerInvalid();                            
    }


    private void testGroomingTypeCreationControllerInvalid() throws Exception {                
        GroomingType t=createGroomingType();
        reset(ts);        
        ObjectMapper objectMapper = new ObjectMapper();        
        // Test with invalid name
        t.setName("");
        String json = objectMapper.writeValueAsString(t);
        t.setName(A_GT_NAME);
        mockMvc.perform(post("/api/v1/groomingtypes")                            
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)                            
                            .content(json))
                .andExpect(status().isBadRequest());                
        verify(ts,never()).save(any(GroomingType.class));
        // Test with invalid Description        
        t.setDescription(null);
        json = objectMapper.writeValueAsString(t);        
        mockMvc.perform(post("/api/v1/groomingtypes")                            
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)                            
                            .content(json))
                .andExpect(status().isBadRequest());                
        verify(ts,never()).save(any(GroomingType.class));
    }


    private void testGroomingTypeCreationControllerOK() throws Exception {
        GroomingType t=createGroomingType();        
        reset(ts);        
        
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(t);

        mockMvc.perform(post("/api/v1/groomingtypes")     
                            .with(csrf())
                            .contentType(MediaType.APPLICATION_JSON)                            
                            .content(json))
                .andExpect(status().isCreated());
        verify(ts).save(any(GroomingType.class));                
    }
    
    public static GroomingType createGroomingType(){
        GroomingType p=new GroomingType();                
        p.setName(A_GT_NAME);
        p.setDescription(A_GT_DESCRIPTION);
        return p;
    }
}
