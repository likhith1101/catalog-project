package org.spring.learningRest;




import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.spring.learningRest.entity.Parameter;
import org.spring.learningRest.repository.ParameterRepository;
import org.spring.learningRest.service.ParameterService;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ParameterServiceTest {

    @InjectMocks
    private ParameterService parameterService;

    @Mock
    private ParameterRepository parameterRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
   
 

    @Test
    void testEditParameter() {
        // Create a sample product for testing
    	Parameter sampleParameter = new Parameter();
    	sampleParameter.setId(1L);
    	sampleParameter.setName("Sample Product");
    	sampleParameter.setInternalName("Internal Sample");
    	sampleParameter.setDetails("Sample Details");
    	
        // Mock the repository behavior
        when(parameterRepository.findById(1L)).thenReturn(Optional.of(sampleParameter));
        when(parameterRepository.save(sampleParameter)).thenReturn(sampleParameter);

//        Product updatedProduct = new Product(1L, "Updated Product", "Internal Updated", "Updated Details", 30);
        Parameter updatedParameter = new Parameter();
    	updatedParameter.setId(1L);
    	updatedParameter.setName("Updated Product");
    	updatedParameter.setInternalName("Internal Updated");
    	updatedParameter.setDetails("Updated Details");
        // updatedParameter.setParameterType(1);
 
        Parameter result = parameterService.editParameter(1L, updatedParameter);

        // Assert that the result is the same as the updated product
        assertEquals(updatedParameter, result);
    }

    @Test
    void testAddFeature() {
        // Create a sample product for testing
    	Parameter sampleParameter = new Parameter();
    	sampleParameter.setId(1L);
    	sampleParameter.setName("Sample Product");
    	sampleParameter.setInternalName("Internal Sample");
    	sampleParameter.setDetails("Sample Details");
        // sampleParameter.setParameterType(1);
    	
        // Mock the repository behavior
        when(parameterRepository.save(sampleParameter)).thenReturn(sampleParameter);

        Parameter result = parameterService.addParameter(sampleParameter);

        // Assert that the result is the same as the sample product
        assertEquals(sampleParameter, result);
    }
}

