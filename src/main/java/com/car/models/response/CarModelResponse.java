package com.car.models.response;

import com.car.models.EnunCarCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarModelResponse {

    private String model;
    private String year;
    private String collor;
    private EnunCarCategory category;

    public ResponseEntity<CarModelResponse> transformResponseEntuty(HttpStatus status) {
        return new ResponseEntity<>(this, status);
    }
}
