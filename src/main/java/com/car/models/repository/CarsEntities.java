package com.car.models.repository;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;

@Getter
public class CarsEntities extends ArrayList<CarEntity> {

    public CarsEntities(Iterable<CarEntity> carEntities) {
        super(new ArrayList<>((Collection<? extends CarEntity>) carEntities));
    }
}
