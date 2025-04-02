package com.carproject.domain;

import jakarta.persistence.*;

@Entity
public class CarVariant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "car_model_id")
    private CarModel carModel;

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

    public CarModel getModel() {
        return carModel;
    }

    public void setModel(CarModel carModel) {
        this.carModel = carModel;
    }
}
