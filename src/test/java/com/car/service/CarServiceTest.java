package com.car.service;

import com.car.models.EnunCarCategory;
import com.car.models.repository.CarEntity;
import com.car.models.repository.CarsEntities;
import com.car.models.response.Car;
import com.car.models.response.Cars;
import com.car.repositories.CarRepository;
import org.mockito.Mock;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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
        CarEntity carEntity = new CarEntity(1L, "gol", "branco", "1999", EnunCarCategory.COMPACT);
        when(carRepository.save(any(CarEntity.class))).thenReturn(carEntity);

        Car car = carService.registerNewCar(new Car(carEntity));

        assertThat(car).isEqualToComparingFieldByField(new Car(carEntity));
    }

    @Test
    public void shouldRecordUpdateCarOnDatabase() {
        CarEntity carEntity = new CarEntity(1L,"gol", "branco","1999", EnunCarCategory.COMPACT);
        when(carRepository.exists(anyLong())).thenReturn(true);
        when(carRepository.save(any(CarEntity.class))).thenReturn(carEntity);

        Car car = carService.upadateCar("1", new Car(carEntity));

        assertThat(car).isEqualToComparingFieldByField(new Car(carEntity));
    }


    @Test
    public void shouldReturnACar() {
        CarEntity carEntity = new CarEntity(1L,"gol", "branco","1999", EnunCarCategory.COMPACT);
        when(carRepository.exists(anyLong())).thenReturn(true);
        when(carRepository.findOne(anyLong())).thenReturn(carEntity);

        Car car = carService.getCarInformation("1");

        assertThat(car).isEqualToComparingFieldByField(new Car(carEntity));
    }

    @Test
    public void shouldReturnAllCars() {
        CarsEntities carsEntities = new CarsEntities();
        carsEntities.add(new CarEntity(1L,"gol", "branco","1999", EnunCarCategory.COMPACT));
        carsEntities.add(new CarEntity(2L, "uno", "preto","2010", EnunCarCategory.COMPACT));

        when(carRepository.findAll()).thenReturn(carsEntities);

        Cars carsResponse = carService.getCarsInformation();

        Cars carsRequested = new Cars(carsEntities);
        assertThat(carsResponse.get(0)).isEqualToComparingFieldByField(carsRequested.get(0));
        assertThat(carsResponse.get(1)).isEqualToComparingFieldByField(carsRequested.get(1));
    }
}