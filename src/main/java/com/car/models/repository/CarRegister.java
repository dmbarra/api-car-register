package com.car.models.repository;


import com.car.models.EnunCarCategory;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Entity
@Table(schema = "car_schema")
public class CarRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String model;
    private String year;
    private String collor;
    private EnunCarCategory category;

    protected CarRegister() {
    }

    public CarRegister(String model,
                       String year,
                       String collor,
                       EnunCarCategory category) {
        this.model = model;
        this.year = year;
        this.collor = collor;
        this.category = category;
    }
}
