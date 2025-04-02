package com.carproject.repo;

import com.carproject.domain.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<CarModel, Long> {
}
