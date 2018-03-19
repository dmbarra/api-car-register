package com.car.models.repository;

import com.car.models.EnunCarCategory;
import com.car.models.response.Car;
import org.testng.annotations.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CarEntityTest {

    @Test
    public void shouldBuildCarFromCarEntity() throws Exception {

        Car car = new Car(1,"gol", "1999", "branco", EnunCarCategory.COMPACT);

        CarEntity carEntity = new CarEntity(car);

        assertThat(car.getId()).isEqualTo(carEntity.getId().intValue());
        assertThat(car.getModel()).isEqualTo(carEntity.getModel());
        assertThat(car.getYear()).isEqualTo(carEntity.getYear());
        assertThat(car.getCollor()).isEqualTo(carEntity.getCollor());
        assertThat(car.getCategory()).isEqualTo(carEntity.getCategory());
    }

    @Test
    public void shouldBuildCarWithIdFromCarEntity() throws Exception {

        Car car = new Car("gol", "1999", "branco", EnunCarCategory.COMPACT);

        CarEntity carEntity = new CarEntity("1" , car);

        assertThat(carEntity.getId()).isEqualTo(1);
        assertThat(carEntity.getModel()).isEqualTo(car.getModel());
        assertThat(carEntity.getYear()).isEqualTo(car.getYear());
        assertThat(carEntity.getCollor()).isEqualTo(carEntity.getCollor());
        assertThat(carEntity.getCategory()).isEqualTo(car.getCategory());
    }
}