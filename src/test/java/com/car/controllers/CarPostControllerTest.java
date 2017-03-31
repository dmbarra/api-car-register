package com.car.controllers;

import com.car.models.CarBodyModel;
import com.car.models.EnunCarCategory;
import com.car.service.CarService;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CarPostControllerTest {

    private CarPostController carPostController;

    @Mock
    private CarService carService;

    @BeforeClass
    public void setUp() {
        initMocks(this);
        carPostController = new CarPostController(carService);
    }

    @Test
    public void shouldReturnSucessWhenCreateNewCar() {
        CarBodyModel carBodyModel = CarBodyModel.builder()
                .collor("branco")
                .model("gol")
                .year("1999")
                .category(EnunCarCategory.COMPACT)
                .build();

        ArgumentCaptor<CarBodyModel> argumentCaptor = ArgumentCaptor.forClass(CarBodyModel.class);
        when(carService.registerNewCar(argumentCaptor.capture()))
                .thenReturn(new ResponseEntity<>("{ id = 123 }", HttpStatus.CREATED));

        ResponseEntity<String> responseEntity = carPostController.carsCreation(carBodyModel);

        verify(carService, times(1)).registerNewCar(carBodyModel);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isEqualTo("{ id = 123 }");

        assertThat(argumentCaptor.getValue().getCollor()).isEqualTo("branco");
        assertThat(argumentCaptor.getValue().getModel()).isEqualTo("gol");
        assertThat(argumentCaptor.getValue().getYear()).isEqualTo("1999");
        assertThat(argumentCaptor.getValue().getCategory()).isEqualTo(EnunCarCategory.COMPACT);
    }
}
