package com.carproject.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "car_brand_id")
    private CarBrand carBrand;

    @OneToMany(mappedBy = "carModel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarVariant> carVariants = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CarBrand getBrand() {
        return carBrand;
    }

    public void setBrand(CarBrand carBrand) {
        this.carBrand = carBrand;
    }

    public List<CarVariant> getVariants() {
        return carVariants;
    }

    public void setVariants(List<CarVariant> carVariants) {
        this.carVariants = carVariants;
    }
}
