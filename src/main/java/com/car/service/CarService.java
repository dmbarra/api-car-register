package com.car.service;

import com.car.models.CarBodyModel;
import com.car.models.repository.CarRegister;
import com.car.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public ResponseEntity<String> registerNewCar(CarBodyModel carBodyModel) {
        CarRegister carRegister = new CarRegister(carBodyModel.getModel(),
                carBodyModel.getYear(),
                carBodyModel.getCollor(),
                carBodyModel.getCategory());

        CarRegister carRegisterDb = carRepository.save(carRegister);

        return new ResponseEntity<>(new String()
                .concat("id:")
                .concat(carRegisterDb.getId().toString()), HttpStatus.CREATED);
    }
}
