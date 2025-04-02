package com.carproject.controller;

import com.carproject.domain.CarModel;
import com.carproject.domain.enums.CarCode;
import com.carproject.repo.BrandRepository;
import com.carproject.repo.ModelRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ModelController {

    private final ModelRepository modelRepository;

    public ModelController(BrandRepository brandRepository, ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @GetMapping("/models")
    public List<String> getModelsByBrand(@RequestParam String brandCode) {
        try {
            CarCode code = CarCode.valueOf(brandCode);
            return modelRepository.findByCarBrand_Code(code)
                    .stream()
                    .map(CarModel::getName)
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            System.out.println("Vigane margikood" + brandCode);
            return List.of();
        }
    }

}
