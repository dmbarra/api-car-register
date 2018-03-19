package com.car.models.response;

import com.car.models.repository.CarsEntities;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class Cars extends ArrayList<Car> {

    public Cars(CarsEntities carsEntities) {
        super(carsEntities.stream()
                .map(Car::new)
                .collect(Collectors.toList()));
    }
}
