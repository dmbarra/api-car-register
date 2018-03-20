package com.car.service;

import com.car.exception.CarException;
import com.car.models.Car;
import com.car.repositories.CarRepository;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Matchers.anyLong;
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
    public void shouldNotRecordUpdateCarOnDatabaseWhenCarEntityIsEmply() {
        when(carRepository.exists(anyLong())).thenReturn(false);
        carService.upadateCar("1", new Car());
    }

    @Test(expectedExceptions = CarException.class)
    public void shouldReturnExceptionWhenNotFoundCar() {
        when(carRepository.exists(anyLong())).thenReturn(false);
        carService.getCarInformation("1");
    }
}
