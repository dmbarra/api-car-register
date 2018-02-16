package com.car.models.repository;


import com.car.models.CarBodyModel;
import com.car.models.EnunCarCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "car_register", schema = "car_schema")
@NoArgsConstructor
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

    public CarRegister(String model,
                       String year,
                       String collor,
                       EnunCarCategory category) {
        this.model = model;
        this.year = year;
        this.collor = collor;
        this.category = category;
    }

    public CarRegister(CarBodyModel carBodyModel) {
        this.collor = carBodyModel.getCollor();
        this.model = carBodyModel.getModel();
        this.category = carBodyModel.getCategory();
        this.year = carBodyModel.getYear();
    }

    public CarRegister(String carId, CarBodyModel carBodyModel) {
        this.id = Long.valueOf(carId);
        this.collor = carBodyModel.getCollor();
        this.model = carBodyModel.getModel();
        this.category = carBodyModel.getCategory();
        this.year = carBodyModel.getYear();

    }
}
