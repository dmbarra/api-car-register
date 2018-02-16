package com.car.models;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class CarBodyModel {

    private String model;
    private String year;
    private String collor;
    private EnunCarCategory category;

    public CarBodyModel(String model,
                        String year,
                        String collor,
                        EnunCarCategory category) {
        this.model = model;
        this.year = year;
        this.collor = collor;
        this.category = category;
    }

    public CarBodyModel() {
    }
}
