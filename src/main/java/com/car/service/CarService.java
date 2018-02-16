package com.car.service;

import com.car.exception.CarException;
import com.car.models.CarBodyModel;
import com.car.models.repository.CarRegister;
import com.car.models.response.CarModelResponse;
import com.car.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Long registerNewCar(CarBodyModel carBodyModel) {
        CarRegister carRegister = new CarRegister(carBodyModel);

        CarRegister carRegisterDb = carRepository.save(carRegister);
        return carRegisterDb.getId();
    }

    public void upadateCar(String carId, CarBodyModel carBodyModel) {
        CarRegister repositoryOne = carRepository.findOne(Long.parseLong(carId));

        if (repositoryOne == null) {
            throw new CarException();
        } else {
            CarRegister carUpdated = new CarRegister(carId, carBodyModel);
            carRepository.save(carUpdated);
        }
    }

    public CarModelResponse getCarInformation(String cardId) {
        CarRegister carRegistered = carRepository.findOne(Long.parseLong(cardId));
        if (carRegistered == null ) {
            throw new CarException();
        }
        return new CarModelResponse(carRegistered);
    }

    public List<CarModelResponse> getCarsInformation() {
        List<CarModelResponse> carModelResponses = new ArrayList<>();
        carRepository.findAll().forEach(car -> carModelResponses.add(new CarModelResponse(car)));
        return carModelResponses;
    }
}
