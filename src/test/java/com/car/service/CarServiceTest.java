package com.car.service;

import com.car.exception.CarException;
import com.car.models.CarBodyModel;
import com.car.models.EnunCarCategory;
import com.car.models.repository.CarRegister;
import com.car.models.response.CarModelResponse;
import com.car.repositories.CarRepository;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class CarServiceTest {

    private CarService carService;

    @Mock
    private CarRepository carRepository;


    @BeforeMethod
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

        CarBodyModel carBodyModel =
                new CarBodyModel("gol", "1999", "branco", EnunCarCategory.COMPACT);

        Long responseEntity = carService.registerNewCar(carBodyModel);

        verify(carRepository, times(1)).save(argumentCaptor.capture());

        assertThat(responseEntity).isEqualTo(1L);

        assertThat(argumentCaptor.getValue().getCollor()).isEqualTo("branco");
        assertThat(argumentCaptor.getValue().getModel()).isEqualTo("gol");
        assertThat(argumentCaptor.getValue().getYear()).isEqualTo("1999");
        assertThat(argumentCaptor.getValue().getCategory()).isEqualTo(EnunCarCategory.COMPACT);
    }

    @Test
    public void shouldRecordUpdateCarOnDatabase() {

        ArgumentCaptor<CarRegister> argumentCaptor = ArgumentCaptor.forClass(CarRegister.class);

        String carId = "1";
        CarRegister carRegister = new CarRegister("gol", "branco","1999", EnunCarCategory.COMPACT);

        when(carRepository.findOne(Long.parseLong(carId))).thenReturn(carRegister);

        CarBodyModel carBodyModel =
                new CarBodyModel("fiat", "1999", "preto", EnunCarCategory.COMPACT);


        carService.upadateCar(carId, carBodyModel);

        verify(carRepository, times(1)).findOne(Long.parseLong(carId));
        verify(carRepository, times(1)).save(argumentCaptor.capture());

        assertThat(argumentCaptor.getValue().getId()).isEqualTo(1L);
        assertThat(argumentCaptor.getValue().getCollor()).isEqualTo("preto");
        assertThat(argumentCaptor.getValue().getModel()).isEqualTo("fiat");
        assertThat(argumentCaptor.getValue().getYear()).isEqualTo("1999");
        assertThat(argumentCaptor.getValue().getCategory()).isEqualTo(EnunCarCategory.COMPACT);
    }


    @Test(expectedExceptions = CarException.class)
    public void shouldNotRecordUpdateCarOnDatabase() {
        when(carRepository.findOne(1L)).thenReturn(null);

        CarBodyModel carBodyModel = new CarBodyModel();

        carService.upadateCar("1", carBodyModel);
    }

    @Test
    public void shouldReturnCar() {
        String carId = "1";
        CarRegister carRegister = new CarRegister("gol", "branco","1999", EnunCarCategory.COMPACT);

        when(carRepository.findOne(Long.parseLong(carId))).thenReturn(carRegister);

        CarModelResponse carModelResponse = carService.getCarInformation(carId);

        verify(carRepository, times(1)).findOne(Long.parseLong(carId));

        assertThat(carModelResponse.getYear()).isEqualTo("branco");
        assertThat(carModelResponse.getCollor()).isEqualTo("1999");
        assertThat(carModelResponse.getModel()).isEqualTo("gol");
        assertThat(carModelResponse.getCategory()).isEqualTo(EnunCarCategory.COMPACT);

    }

    @Test(expectedExceptions = CarException.class)
    public void shouldReturnExceptionWhenNotFoundCar() {
        String carId = "1";
        when(carRepository.findOne(Long.parseLong(carId))).thenReturn(null);

        carService.getCarInformation(carId);
    }

    @Test
    public void shouldReturnAllCars() {
        CarRegister carRegister = new CarRegister("gol", "branco","1999", EnunCarCategory.COMPACT);

        when(carRepository.findAll()).thenReturn(Collections.singleton(carRegister));

        List<CarModelResponse> carModelResponse = carService.getCarsInformation();

        verify(carRepository, times(1)).findAll();

        assertThat(carModelResponse.get(0).getYear()).isEqualTo("branco");
        assertThat(carModelResponse.get(0).getCollor()).isEqualTo("1999");
        assertThat(carModelResponse.get(0).getModel()).isEqualTo("gol");
        assertThat(carModelResponse.get(0).getCategory()).isEqualTo(EnunCarCategory.COMPACT);

    }
}