package com.car.service;

import com.car.exception.CarException;
import com.car.models.repository.CarEntity;
import com.car.models.response.Car;
import com.car.repositories.CarRepository;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CarServiceExceptionsTest {

    private CarService carService;

    @Mock
    private CarRepository carRepository;

    @BeforeMethod
    public void setUp() {
        initMocks(this);
        carService = new CarService(carRepository);
    }

    @Test(expectedExceptions = CarException.class)
    public void shouldNotRecordUpdateCarOnDatabase() {
        when(carRepository.findOne(1L)).thenReturn(null);

        Car car = new Car();

        carService.upadateCar("1", car);
    }

    @Test(expectedExceptions = CarException.class)
    public void shouldNotRecordUpdateCarOnDatabaseWhenCarEntityIsEmply() {
        when(carRepository.findOne(1L)).thenReturn(new CarEntity());

        Car car = new Car();

        carService.upadateCar("1", car);
    }

    @Test(expectedExceptions = CarException.class)
    public void shouldReturnExceptionWhenNotFoundCar() {
        String carId = "1";
        when(carRepository.findOne(Long.parseLong(carId))).thenReturn(null);

        carService.getCarInformation(carId);
    }


    @Test(expectedExceptions = CarException.class)
    public void shouldReturnExceptionWhenEmptyCar() {
        String carId = "1";
        when(carRepository.findOne(Long.parseLong(carId))).thenReturn(new CarEntity());

        carService.getCarInformation(carId);
    }
}
