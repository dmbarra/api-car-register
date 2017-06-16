package com.car.controllers;

import com.car.models.CarBodyModel;
import com.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarPostController {

    CarService carService;

    @Autowired
    public CarPostController(CarService carService) {
        this.carService = carService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/car", method = RequestMethod.POST)
    public ResponseEntity<String> carsCreation(@RequestBody CarBodyModel carBodyModel) {
       return carService.registerNewCar(carBodyModel);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/car/{carId}", method = RequestMethod.PUT)
    public ResponseEntity<String> carsUpdate(@PathVariable String carId,
                                             @RequestBody CarBodyModel carBodyModel) {
        return carService.upadateCar(carId, carBodyModel);
    }
}
