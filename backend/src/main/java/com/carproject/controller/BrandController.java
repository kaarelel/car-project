package com.carproject.controller;

import com.carproject.repo.BrandRepository;
import com.carproject.response.BrandDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BrandController {

    private final BrandRepository brandRepository;

    public BrandController(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @GetMapping("/brands")
    public List<BrandDto> getAllBrands() {
        return brandRepository.findAll().stream()
                .map(brand -> new BrandDto(
                        brand.getCode().name(),
                        brand.getName() != null ? brand.getName() : brand.getCode().name()
                ))
                .sorted((a, b) -> a.name().compareToIgnoreCase(b.name()))
                .toList();
    }
}
