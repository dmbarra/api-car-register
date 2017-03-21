package com.car.repositories;

import com.car.models.repository.CarRegister;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<CarRegister, Long> {
}
