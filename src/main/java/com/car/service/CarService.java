package com.car.service;

import com.car.exception.CarException;
import com.car.models.repository.CarEntity;
import com.car.models.repository.CarsEntities;
import com.car.models.Car;
import com.car.models.Cars;
import com.car.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.Long.valueOf;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car registerNewCar(Car car) {
        return new Car(carRepository.save(new CarEntity(car)));
    }

    public Car upadateCar(String carId, Car car) throws CarException {

        if (carRepository.exists(valueOf(carId))) {
            return new Car(carRepository.save(new CarEntity(carId, car)));
        }
        throw new CarException();
    }

    public Car getCarInformation(String carId) {

        if (carRepository.exists(valueOf(carId))) {
            return new Car(carRepository.findOne(valueOf(carId)));
        }
        throw new CarException();
    }

    public Cars getCarsInformation() {
        return new Cars(new CarsEntities(carRepository.findAll()));
    }
}
