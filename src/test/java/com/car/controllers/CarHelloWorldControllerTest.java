package com.car.controllers;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CarHelloWorldControllerTest {

    CarHelloWorldController carHelloWorldController;

    @BeforeClass
    public void setUP() {
        carHelloWorldController = new CarHelloWorldController();
    }

    @Test
    public void shouldReturnHelloHenrique() {
        String response = carHelloWorldController.index();

        assertThat(response).isEqualTo("Oi Henrique");
    }

}