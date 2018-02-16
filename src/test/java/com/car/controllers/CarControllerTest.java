package com.car.controllers;

import com.car.models.CarBodyModel;
import com.car.models.EnunCarCategory;
import com.car.models.response.CarModelResponse;
import com.car.service.CarService;
import groovy.lang.Singleton;
import org.hibernate.mapping.Collection;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class CarControllerTest {


    @Mock
    private CarService carService;

    @InjectMocks
    private CarController carController;

    @Test
    public void shouldReturnSucessWhenCreateNewCar() {
        CarBodyModel carBodyModel = new CarBodyModel("gol", "1999", "branco", EnunCarCategory.COMPACT);

        ArgumentCaptor<CarBodyModel> argumentCaptor = ArgumentCaptor.forClass(CarBodyModel.class);
        when(carService.registerNewCar(argumentCaptor.capture()))
                .thenReturn(new ResponseEntity<>("{ id = 123 }", HttpStatus.CREATED));

        ResponseEntity<String> responseEntity = carController.carsCreation(carBodyModel);

        verify(carService, times(1)).registerNewCar(carBodyModel);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isEqualTo("{ id = 123 }");

        assertThat(argumentCaptor.getValue().getCollor()).isEqualTo("branco");
        assertThat(argumentCaptor.getValue().getModel()).isEqualTo("gol");
        assertThat(argumentCaptor.getValue().getYear()).isEqualTo("1999");
        assertThat(argumentCaptor.getValue().getCategory()).isEqualTo(EnunCarCategory.COMPACT);
    }

    @Test
    public void shouldReturnSucessWhenUpdatedCar() {

        CarBodyModel carBodyModel = new CarBodyModel("gol", "1999", "branco", EnunCarCategory.COMPACT);

        ArgumentCaptor<CarBodyModel> argumentCaptor = ArgumentCaptor.forClass(CarBodyModel.class);
        when(carService.upadateCar(anyString(), argumentCaptor.capture()))
                .thenReturn(new ResponseEntity<>(HttpStatus.OK));

        ResponseEntity<String> responseEntity = carController.carsUpdate("1", carBodyModel);

        verify(carService, times(1)).upadateCar("1", carBodyModel);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(argumentCaptor.getValue().getCollor()).isEqualTo("branco");
        assertThat(argumentCaptor.getValue().getModel()).isEqualTo("gol");
        assertThat(argumentCaptor.getValue().getYear()).isEqualTo("1999");
        assertThat(argumentCaptor.getValue().getCategory()).isEqualTo(EnunCarCategory.COMPACT);
    }

    @Test
    public void shouldReturnInformationAboutCar() {
         CarModelResponse value =
                new CarModelResponse("gol","1999","branco",EnunCarCategory.COMPACT);

        when(carService.getCarInformation(anyString())).thenReturn(value);

        ResponseEntity<CarModelResponse> responseEntity = carController.carsReturnSingleCar("1" );

        verify(carService, times(1)).getCarInformation("1");
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getCollor()).isEqualTo("branco");
        assertThat(responseEntity.getBody().getModel()).isEqualTo("gol");
        assertThat(responseEntity.getBody().getYear()).isEqualTo("1999");
        assertThat(responseEntity.getBody().getCategory()).isEqualTo(EnunCarCategory.COMPACT);
    }

    @Test
    public void shouldReturnListOfCar() {
        CarModelResponse value =
                new CarModelResponse("gol","1999","branco",EnunCarCategory.COMPACT);

        when(carService.getCarsInformation()).thenReturn(Collections.singletonList(value));

        ResponseEntity<List<CarModelResponse>> responseEntity = carController.carsReturnAllCars();

        verify(carService, times(1)).getCarsInformation();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().get(0).getCollor()).isEqualTo("branco");
        assertThat(responseEntity.getBody().get(0).getModel()).isEqualTo("gol");
        assertThat(responseEntity.getBody().get(0).getYear()).isEqualTo("1999");
        assertThat(responseEntity.getBody().get(0).getCategory())
                .isEqualTo(EnunCarCategory.COMPACT);
    }
}
