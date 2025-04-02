package com.carproject.domain;

import com.carproject.domain.enums.CarCode;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CarBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private CarCode code;

    private String name;

    @OneToMany(mappedBy = "carBrand", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarModel> carModels = new ArrayList<>();

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

    public List<CarModel> getModels() {
        return carModels;
    }

    public void setModels(List<CarModel> carModels) {
        this.carModels = carModels;
    }

    public CarCode getCode() {
        return code;
    }

    public void setCode(CarCode code) {
        this.code = code;
    }
}
