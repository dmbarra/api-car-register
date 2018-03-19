package com.car.models.response;

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

    public Car(CarEntity carRegistered) {
        this.id = carRegistered.getId() != null ? carRegistered.getId().intValue() : 0;
        this.model = carRegistered.getModel();
        this.year = carRegistered.getYear();
        this.collor = carRegistered.getCollor();
        this.category = carRegistered.getCategory();
    }

    public Car(String model, String year, String collor, EnunCarCategory category) {
        this.model = model;
        this.year = year;
        this.collor = collor;
        this.category = category;
    }
}
