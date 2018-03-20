package com.car.models.response;

import com.car.models.Car;
import com.car.models.EnunCarCategory;
import com.car.models.repository.CarEntity;
import org.testng.annotations.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CarTest {

    @Test
    public void shouldBuildCarEntityFromCar() throws Exception {

        CarEntity carEntity = new CarEntity(1L,"gol", "1999", "branco", EnunCarCategory.COMPACT);

        Car car = new Car(carEntity);

        assertThat(carEntity.getId().intValue()).isEqualTo(car.getId());
        assertThat(carEntity.getModel()).isEqualTo(car.getModel());
        assertThat(carEntity.getYear()).isEqualTo(car.getYear());
        assertThat(carEntity.getCollor()).isEqualTo(car.getCollor());
        assertThat(carEntity.getCategory()).isEqualTo(car.getCategory());
    }
}