package com.car.service;

import com.car.exception.CarException;
import com.car.models.EnunCarCategory;
import com.car.models.repository.CarEntity;
import com.car.models.response.Car;
import com.car.repositories.CarRepository;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
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
        ArgumentCaptor<CarEntity> argumentCaptor = ArgumentCaptor.forClass(CarEntity.class);

        CarEntity carEntity = mock(CarEntity.class);

        when(carEntity.getId()).thenReturn(1L);

        when(carRepository.save(argumentCaptor.capture()))
                .thenReturn(carEntity);

        Car carRequest =
                new Car("gol", "1999", "branco", EnunCarCategory.COMPACT);

        Car car = carService.registerNewCar(carRequest);

        verify(carRepository, times(1)).save(argumentCaptor.capture());

        assertThat(car.getId()).isEqualTo(1);

        assertThat(argumentCaptor.getValue().getCollor()).isEqualTo("branco");
        assertThat(argumentCaptor.getValue().getModel()).isEqualTo("gol");
        assertThat(argumentCaptor.getValue().getYear()).isEqualTo("1999");
        assertThat(argumentCaptor.getValue().getCategory()).isEqualTo(EnunCarCategory.COMPACT);
    }

    @Test
    public void shouldRecordUpdateCarOnDatabase() {

        ArgumentCaptor<CarEntity> argumentCaptor = ArgumentCaptor.forClass(CarEntity.class);

        String carId = "1";
        CarEntity carEntity = new CarEntity(1L,"gol", "branco","1999", EnunCarCategory.COMPACT);

        when(carRepository.findOne(Long.parseLong(carId))).thenReturn(carEntity);

        Car car = new Car(1,"fiat", "1999", "preto", EnunCarCategory.COMPACT);
        when(carRepository.save(argumentCaptor.capture())).thenReturn(carEntity);

        carService.upadateCar(carId, car);

        verify(carRepository, times(1)).findOne(Long.parseLong(carId));
        verify(carRepository, times(1)).save(argumentCaptor.capture());

        assertThat(new Car(argumentCaptor.getValue())).isEqualToComparingFieldByField(car);
    }


    @Test(expectedExceptions = CarException.class)
    public void shouldNotRecordUpdateCarOnDatabase() {
        when(carRepository.findOne(1L)).thenReturn(null);

        Car car = new Car();

        carService.upadateCar("1", car);
    }

    @Test
    public void shouldReturnCar() {
        String carId = "1";
        CarEntity carEntity = new CarEntity("gol", "branco","1999", EnunCarCategory.COMPACT);

        when(carRepository.findOne(Long.parseLong(carId))).thenReturn(carEntity);

        Car car = carService.getCarInformation(carId);

        verify(carRepository, times(1)).findOne(Long.parseLong(carId));

        assertThat(car)
                .isEqualToComparingOnlyGivenFields(carEntity,
                        "model", "year", "collor", "category");
    }

    @Test(expectedExceptions = CarException.class)
    public void shouldReturnExceptionWhenNotFoundCar() {
        String carId = "1";
        when(carRepository.findOne(Long.parseLong(carId))).thenReturn(null);

        carService.getCarInformation(carId);
    }

    @Test
    public void shouldReturnAllCars() {
        CarEntity carEntity = new CarEntity("gol", "branco","1999", EnunCarCategory.COMPACT);

        when(carRepository.findAll()).thenReturn(Collections.singleton(carEntity));

        List<Car> car = carService.getCarsInformation();

        verify(carRepository, times(1)).findAll();

        assertThat(car.get(0))
                .isEqualToComparingOnlyGivenFields(carEntity,
                        "model", "year", "collor", "category");
    }
}