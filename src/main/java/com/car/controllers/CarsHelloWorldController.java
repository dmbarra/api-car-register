package com.car.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarsHelloWorldController {

    @RequestMapping("/")
    public String index() {
        return "Oi Henrique";
    }
}
