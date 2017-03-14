package com.car.controllers;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CarsHelloWorldControllerTest {

    CarsHelloWorldController carsHelloWorldController;

    @BeforeClass
    public void setUP() {
        carsHelloWorldController = new CarsHelloWorldController();
    }

    @Test
    public void shouldReturnHelloHenrique() {
        String response = carsHelloWorldController.index();

        assertThat(response).isEqualTo("Oi Henrique");
    }

}