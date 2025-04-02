package com.carproject.repo;

import com.carproject.domain.CarVariant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariantRepository extends JpaRepository<CarVariant, Long> {
}
