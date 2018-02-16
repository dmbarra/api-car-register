package com.car.service;

import com.car.exception.CarException;
import com.car.models.CarBodyModel;
import com.car.models.repository.CarRegister;
import com.car.models.response.CarModelResponse;
import com.car.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.smartcardio.CardException;
import java.util.ArrayList;
import java.util.List;

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

    public ResponseEntity<String> upadateCar(String carId, CarBodyModel carBodyModel) {

        if (carRepository.findOne(Long.parseLong(carId)) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } else {

            CarRegister carUpdated = new CarRegister(Long.parseLong(carId), carBodyModel.getModel(),
                    carBodyModel.getYear(),
                    carBodyModel.getCollor(),
                    carBodyModel.getCategory());

            carRepository.save(carUpdated);

            return new ResponseEntity<>(new String(), HttpStatus.NO_CONTENT);
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
        return new ArrayList<>();
    }
}
