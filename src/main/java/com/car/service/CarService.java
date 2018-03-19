package com.car.service;

import com.car.exception.CarException;
import com.car.models.repository.CarEntity;
import com.car.models.repository.CarsEntities;
import com.car.models.response.Car;
import com.car.models.response.Cars;
import com.car.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Car upadateCar(String carId, Car car) {
        CarEntity repositoryOne = carRepository.findOne(Long.parseLong(carId));

        if (repositoryOne == null) {
            throw new CarException();
        } else {
            CarEntity carUpdated = new CarEntity(carId, car);
            return new Car(carRepository.save(carUpdated));
        }
    }

    public Car getCarInformation(String cardId) {
        CarEntity carRegistered = carRepository.findOne(Long.parseLong(cardId));
        if (carRegistered == null) {
            throw new CarException();
        }
        return new Car(carRegistered);
    }

    public Cars getCarsInformation() {
        return new Cars(new CarsEntities(carRepository.findAll()));
    }
}
