package org.springframework.samples.petclinic.product;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.grooming.GroomingType;
import org.springframework.samples.petclinic.grooming.GroomingTypeController;
import org.springframework.samples.petclinic.grooming.GroomingTypeService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = GroomingTypeController.class,		
		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
		excludeAutoConfiguration= SecurityConfiguration.class)
public class Test8 {
    @MockBean
	GroomingTypeService ts;	    	

    @Autowired
	private MockMvc mockMvc;

    @WithMockUser(value = "spring")
    @Test
	void test8() throws Exception {			
		testGetGroomingTypes();
        testGetValidGroomingType();
		testGetGroomingTypeNotFound();
	}

	private void testGetGroomingTypes() throws Exception {
        reset(ts);		
		when(ts.getAllGroomingTypes()).thenReturn(List.of());
		mockMvc.perform(get("/api/v1/groomingtypes"))
			.andExpect(status().isOk());		
		verify(ts).getAllGroomingTypes();
    }

    private void testGetGroomingTypeNotFound() throws Exception {
		reset(ts);
		Integer groomingTypeId=1;
		when(ts.getGroomingTypeById(any(Integer.class))).thenReturn(null);
		mockMvc.perform(get("/api/v1/groomingtypes/"+groomingTypeId))
			.andExpect(status().isNotFound());		
		verify(ts).getGroomingTypeById(groomingTypeId);
	}

	private void testGetValidGroomingType() throws Exception {
		reset(ts);
		Integer groomingTypeId=1;
		GroomingType t=createValidGroomingType();		
		when(ts.getGroomingTypeById(groomingTypeId)).thenReturn(t);

		mockMvc.perform(get("/api/v1/groomingtypes/"+groomingTypeId))
				.andExpect(status().isOk())				
				.andExpect(jsonPath("$.name", is(t.getName())))
				.andExpect(jsonPath("$.description", is(t.getDescription())));		
		verify(ts).getGroomingTypeById(groomingTypeId);
	}

    private GroomingType createValidGroomingType() {
        GroomingType t=new GroomingType();
        t.setName("A valid GroomingType Name");
        t.setDescription("This is a valid GroomingType");
        return t;
    }
}