package com.carproject.repo;

import com.carproject.domain.CarModel;
import com.carproject.domain.enums.CarCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<CarModel, Integer> {
    List<CarModel> findByCarBrand_Code(CarCode code);
}