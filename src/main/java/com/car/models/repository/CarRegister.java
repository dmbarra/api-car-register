package com.car.models.repository;


import com.car.models.EnunCarCategory;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Getter
@Entity
@Table(name = "car_register", schema = "car_schema")
public class CarRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_register_id_seq")
    @SequenceGenerator(name = "car_register_id_seq",
            sequenceName = "car_schema.car_register_id_seq",
            allocationSize = 1)
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