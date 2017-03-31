package com.car.service;

import com.car.models.CarBodyModel;
import com.car.models.EnunCarCategory;
import com.car.models.repository.CarRegister;
import com.car.repositories.CarRepository;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CarServiceTest {

    CarService carService;

    @Mock
    CarRepository carRepository;


    @BeforeClass
    public void setUp() {
        initMocks(this);
        carService = new CarService(carRepository);
    }

    @Test
    public void shouldRecordNewCarOnDatabase() {
        ArgumentCaptor<CarRegister> argumentCaptor = ArgumentCaptor.forClass(CarRegister.class);

        CarRegister carRegister = mock(CarRegister.class);
        when(carRegister.getId()).thenReturn(1L);

        when(carRepository.save(argumentCaptor.capture()))
                .thenReturn(carRegister);

        CarBodyModel carBodyModel = CarBodyModel
                .builder()
                .model("gol")
                .collor("branco")
                .year("1999")
                .category(EnunCarCategory.COMPACT)
                .build();

        ResponseEntity<String> responseEntity = carService.registerNewCar(carBodyModel);

        verify(carRepository, times(1)).save(argumentCaptor.capture());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).contains("id:1");

        assertThat(argumentCaptor.getValue().getCollor()).isEqualTo("branco");
        assertThat(argumentCaptor.getValue().getModel()).isEqualTo("gol");
        assertThat(argumentCaptor.getValue().getYear()).isEqualTo("1999");
        assertThat(argumentCaptor.getValue().getCategory()).isEqualTo(EnunCarCategory.COMPACT);
    }

}