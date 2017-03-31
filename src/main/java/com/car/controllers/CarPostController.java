package com.car.controllers;

import com.car.models.CarBodyModel;
import com.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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

}
