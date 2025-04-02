package com.carproject.repo;

import com.carproject.domain.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<CarBrand, Long> {
}
