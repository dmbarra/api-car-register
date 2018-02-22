package com.car.models.response;

import com.car.models.EnunCarCategory;
import com.car.models.repository.CarRegister;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarModelResponse {

    private int id;
    private String model;
    private String year;
    private String collor;
    private EnunCarCategory category;

    public CarModelResponse(CarRegister carRegistered) {
        this.id = carRegistered.getId() != null ? carRegistered.getId().intValue() : 0;
        this.model = carRegistered.getModel();
        this.year = carRegistered.getYear();
        this.collor = carRegistered.getCollor();
        this.category = carRegistered.getCategory();
    }

    public ResponseEntity<CarModelResponse> transformResponseEntuty(HttpStatus status) {
        return new ResponseEntity<>(this, status);
    }
}
