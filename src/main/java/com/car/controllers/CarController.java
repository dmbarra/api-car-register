package com.car.controllers;

import com.car.exception.CarException;
import com.car.models.Car;
import com.car.models.Cars;
import com.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/car", produces = MediaType.APPLICATION_JSON_VALUE)
    public Car carsCreation(@RequestBody Car car) {
        return carService.registerNewCar(car);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/car/{carId}")
    public Car carsUpdate(@PathVariable String carId,
                          @RequestBody Car car) {
        return carService.upadateCar(carId, car);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/car/{carId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Car carsReturnSingleCar(@PathVariable String carId) {
        return carService.getCarInformation(carId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/car/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Cars carsReturnAllCars() {
        return carService.getCarsInformation();
    }

    @ExceptionHandler({CarException.class})
    public ResponseEntity<String> handleException() {
        return new ResponseEntity<>("Errou!!!!", HttpStatus.BAD_REQUEST);
    }
}
