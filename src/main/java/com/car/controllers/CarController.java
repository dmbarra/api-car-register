package com.car.controllers;

import com.car.models.CarBodyModel;
import com.car.models.response.CarModelResponse;
import com.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/car", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> carsCreation(@RequestBody CarBodyModel carBodyModel) {
        Long result = carService.registerNewCar(carBodyModel);
       return transformIntoEntity(result, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/car/{carId}", method = RequestMethod.PUT)
    public ResponseEntity<String> carsUpdate(@PathVariable String carId,
                                             @RequestBody CarBodyModel carBodyModel) {
        return carService.upadateCar(carId, carBodyModel);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/car/{carId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarModelResponse> carsReturnSingleCar(@PathVariable String carId) {
        return carService.getCarInformation(carId).transformResponseEntuty(HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/car/", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<CarModelResponse>> carsReturnAllCars() {
        return new ResponseEntity<>(carService.getCarsInformation(), HttpStatus.OK);
    }

    private ResponseEntity<String> transformIntoEntity(Long result, HttpStatus status) {
        return new ResponseEntity<>(new String()
                .concat("{")
                .concat("id:")
                .concat(result.toString()
                .concat("}")), status);
    }
}
