package com.carproject.controller;

import com.carproject.domain.CarBrand;
import com.carproject.domain.CarModel;
import com.carproject.domain.enums.CarCode;
import com.carproject.repo.ModelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ModelControllerTest {

    private ModelRepository modelRepository;
    private ModelController modelController;

    @BeforeEach
    void setUp() {
        modelRepository = mock(ModelRepository.class);
        modelController = new ModelController(modelRepository);
    }

    @Test
    void getModelsForValidBrand_shouldReturnModels() {
        CarBrand bmw = new CarBrand();
        bmw.setCode(CarCode.BMW);

        CarModel m1 = new CarModel();
        m1.setName("316");
        m1.setCarBrand(bmw);

        CarModel m2 = new CarModel();
        m2.setName("318");
        m2.setCarBrand(bmw);

        when(modelRepository.findByCarBrand_Code(CarCode.BMW)).thenReturn(List.of(m1, m2));

        List<String> response = modelController.getModelsForBrand("BMW");

        assertNotNull(response);
        assertEquals(2, response.size());
        assertTrue(response.contains("316"));
        assertTrue(response.contains("318"));
    }

    @Test
    void getModelsForInvalidBrand_shouldReturnEmptyList() {
        List<String> response = modelController.getModelsForBrand("TOYOTA");

        assertNotNull(response);
        assertTrue(response.isEmpty());
    }
}
