package com.car.models;

import com.car.models.EnunCarCategory;
import com.car.models.repository.CarEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private int id;
    private String model;
    private String year;
    private String collor;
    private EnunCarCategory category;

    public Car(CarEntity carEntity) {
        this.id = carEntity.getId() != null ? carEntity.getId().intValue() : 0;
        this.model = carEntity.getModel();
        this.year = carEntity.getYear();
        this.collor = carEntity.getCollor();
        this.category = carEntity.getCategory();
    }

    public Car(String model, String year, String collor, EnunCarCategory category) {
        this.model = model;
        this.year = year;
        this.collor = collor;
        this.category = category;
    }
}
