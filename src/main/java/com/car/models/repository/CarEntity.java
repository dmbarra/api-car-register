package com.car.models.repository;


import com.car.models.EnunCarCategory;
import com.car.models.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import static java.lang.Long.valueOf;

@Getter
@Entity
@Table(name = "car_register", schema = "car_schema")
@NoArgsConstructor
@AllArgsConstructor
public class CarEntity {

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

    public CarEntity(Car car) {
        this.id = valueOf((car.getId() > 0) ? car.getId() : 0);
        this.collor = car.getCollor();
        this.model = car.getModel();
        this.category = car.getCategory();
        this.year = car.getYear();
    }

    public CarEntity(String carId, Car car) {
        this.id = valueOf(carId);
        this.collor = car.getCollor();
        this.model = car.getModel();
        this.category = car.getCategory();
        this.year = car.getYear();

    }
}
