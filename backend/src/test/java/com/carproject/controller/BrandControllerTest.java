package com.carproject.controller;

import com.carproject.domain.CarBrand;
import com.carproject.domain.enums.CarCode;
import com.carproject.repo.BrandRepository;
import com.carproject.response.BrandDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BrandControllerTest {

    private BrandRepository brandRepository;
    private BrandController brandController;

    @BeforeEach
    void setUp() {
        brandRepository = mock(BrandRepository.class);
        brandController = new BrandController(brandRepository);
    }

    @Test
    void testGetAllBrands_shouldReturnSortedBrands() {
        CarBrand bmw = new CarBrand();
        bmw.setCode(CarCode.BMW);
        bmw.setName("BMW");

        CarBrand audi = new CarBrand();
        audi.setCode(CarCode.AUDI);
        audi.setName("Audi");

        when(brandRepository.findAll()).thenReturn(List.of(bmw, audi));

        List<BrandDto> result = brandController.getAllBrands();

        assertEquals(2, result.size());
        assertEquals("Audi", result.get(0).name());
        assertEquals("BMW", result.get(1).name());
    }
}
