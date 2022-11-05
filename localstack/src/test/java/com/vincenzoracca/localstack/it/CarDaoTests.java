package com.vincenzoracca.localstack.it;

import com.vincenzoracca.localstack.dao.CarDao;
import com.vincenzoracca.localstack.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(LocalStackConfiguration.class)
@ActiveProfiles("test")
class CarDaoTests {

    @Autowired
    private CarDao CarDao;

    @BeforeEach
    public void setUp() {
        CarDao.deleteAll();
    }


    @Test
    void saveNewElementTest() {
        var carOne = new Car(null, "123456789", "Fiat");

        CarDao.save(carOne);

        List<Car> retrievedCars = CarDao.findAll();
        assertThat(retrievedCars).hasSize(1);
        assertThat(retrievedCars.get(0)).isEqualTo(carOne);
    }

    @Test
    void saveAnExistingElementTest() {
        var carOne = new Car(null, "123456789", "Fiat");

        CarDao.save(carOne);

        List<Car> retrievedCars = CarDao.findAll();
        assertThat(retrievedCars).hasSize(1);
        assertThat(retrievedCars.get(0)).isEqualTo(carOne);
        assertThat(retrievedCars.get(0).getName()).isEqualTo("Fiat");

        carOne.setName("Mercedes");
        CarDao.save(carOne);

        retrievedCars = CarDao.findAll();
        assertThat(retrievedCars).hasSize(1);
        assertThat(retrievedCars.get(0)).isEqualTo(carOne);
        assertThat(retrievedCars.get(0).getName()).isEqualTo("Mercedes");
    }

    @Test
    void deleteTest() {
        var carOne = new Car(null, "123456789", "Fiat");

        CarDao.save(carOne);
        String id = carOne.getId();
        assertThat(id).isNotNull();

        Optional<Car> retrievedCar = CarDao.findById(id);
        assertThat(retrievedCar.isPresent()).isTrue();
        assertThat(retrievedCar.get()).isEqualTo(carOne);

        CarDao.delete(carOne);
        Optional<Car> CarNotRetrieved = CarDao.findById(id);
        assertThat(CarNotRetrieved.isPresent()).isFalse();

    }
}
