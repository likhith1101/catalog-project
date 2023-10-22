// package com.spring.learningRest;

// import com.spring.learningRest.controller.FeatureController;
// import com.spring.learningRest.entity.Feature;
// import com.spring.learningRest.repository.FeatureRepository;
// import com.spring.learningRest.repository.ProductRepository;
// import com.spring.learningRest.service.FeatureService;
// import com.spring.learningRest.service.ProductService;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

// import java.util.ArrayList;
// import java.util.List;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.when;

// @SpringBootTest
// public class FeatureControllerTest {

//     @Autowired
//     private FeatureController featureController;

//     @MockBean
//     private FeatureRepository featureRepository;

//     @MockBean
//     private FeatureService featureService;

   
//     @Test
//     void testGetAllFeatures() {
//         List<Feature> mockFeatures = new ArrayList<>();
//         // Add some mock features to the list

//         when(featureRepository.findAll()).thenReturn(mockFeatures);

//         List<Feature> response = featureController.getAllFeatures();

//         assertEquals(mockFeatures, response);
//     }

//     @Test
//     void testGetFeatureById() {
//         Long featureId = 1L;
//         Feature mockFeature = new Feature();
//         mockFeature.setId(featureId);
//         // Set other properties for the mock feature

//         when(featureRepository.findById(featureId)).thenReturn(java.util.Optional.of(mockFeature));

//         ResponseEntity<Feature> response = featureController.getFeatureById(featureId);

//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(mockFeature, response.getBody());
//     }

//     @Test
//     void testGetFeatureByIdNotFound() {
//         Long featureId = 2L;

//         when(featureRepository.findById(featureId)).thenReturn(java.util.Optional.empty());

//         ResponseEntity<Feature> response = featureController.getFeatureById(featureId);

//         assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//     }

//     @Test
//     void testAddFeature() {
//         Feature mockFeature = new Feature();
//         // Set properties for the mock feature

//         when(featureService.addFeature(any(Feature.class))).thenReturn(mockFeature);

//         ResponseEntity<Feature> response = featureController.addFeature(mockFeature);

//         assertEquals(HttpStatus.CREATED, response.getStatusCode());
//         assertEquals(mockFeature, response.getBody());
//     }

//     @Test
//     void testAddFeatureError() {
//         when(featureService.addFeature(any(Feature.class))).thenThrow(new IllegalArgumentException("Invalid feature"));

//         ResponseEntity<Feature> response = featureController.addFeature(new Feature());

//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals("Error", response.getBody().getName());
//     }

//     @Test
//     void testEditFeature() {
//         Long featureId = 1L;
//         Feature updatedFeature = new Feature();
//         updatedFeature.setId(featureId);
//         // Set other properties for the updated feature

//         when(featureService.editFeature(featureId, updatedFeature)).thenReturn(updatedFeature);

//         ResponseEntity<Feature> response = featureController.editFeature(featureId, updatedFeature);

//         assertEquals(HttpStatus.OK, response.getStatusCode());
//         assertEquals(updatedFeature, response.getBody());
//     }

//     @Test
//     void testEditFeatureNotFound() {
//         Long featureId = 2L;

//         when(featureService.editFeature(featureId, new Feature())).thenReturn(null);

//         ResponseEntity<Feature> response = featureController.editFeature(featureId, new Feature());

//         assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//     }

//     @Test
//     void testDeleteFeature() {
//         Long featureId = 1L;
//         Feature mockFeature = new Feature();
//         // Set properties for the mock feature

//         when(featureRepository.findById(featureId)).thenReturn(java.util.Optional.of(mockFeature));

//         ResponseEntity<Void> response = featureController.deleteFeature(featureId);

//         assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
//     }

//     @Test
//     void testDeleteFeatureNotFound() {
//         Long featureId = 2L;

//         when(featureRepository.findById(featureId)).thenReturn(java.util.Optional.empty());

//         ResponseEntity<Void> response = featureController.deleteFeature(featureId);

//         assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
//     }
// }



