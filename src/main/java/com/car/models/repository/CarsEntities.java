package com.car.models.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Getter
@NoArgsConstructor
public class CarsEntities extends ArrayList<CarEntity> {

    public CarsEntities(Iterable<CarEntity> carEntities) {
        super(new ArrayList<>((Collection<? extends CarEntity>) carEntities));
    }
}
