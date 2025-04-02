package com.carproject.controller;

import com.carproject.domain.CarModel;
import com.carproject.domain.enums.CarCode;
import com.carproject.repo.ModelRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ModelController {

    private final ModelRepository modelRepository;

    public ModelController(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @GetMapping("/models")
    public List<String> getModelsForBrand(@RequestParam String brandCode) {
        CarCode code = CarCode.fromString(brandCode);

        if (code == null || code == CarCode.MUU) {
            System.out.println("Vigane margikood või käsitsi sisestus: " + brandCode);
            return List.of();
        }

        return modelRepository.findByCarBrand_Code(code)
                .stream()
                .map(CarModel::getName)
                .toList();
    }
}
