package component

import com.car.CarsApi
import com.car.models.Car
import com.car.models.repository.CarEntity
import com.car.repositories.CarRepository
import org.json.JSONObject
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.*
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

import static com.car.models.EnunCarCategory.PICKUP

@ContextConfiguration(classes = CarsApi.class,
        loader = SpringBootContextLoader.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarTest extends Specification{

    @Autowired
    private CarRepository carRepository;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    def 'should return status created when created new register'() {
        given:('I have a new car for Register ')
        def car = new Car("gol", "1999", "branco", PICKUP)

        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        HttpEntity<Car> entity = new HttpEntity<Car>(car, headers)

        when:('I send new car information')
        def response = restTemplate.postForEntity('/car', entity, String.class)

        then:('I show the status created and id of data base')
        response.statusCode == HttpStatus.CREATED
        def responseBody =  new JSONObject(response.body)
        responseBody.id > 0
        responseBody.model == "gol"
        responseBody.year == "1999"
        responseBody.collor == "branco"
        responseBody.category == "PICKUP"
    }

    @Test
    def 'should return status no content when update register'() {
        given:('I have a car registed')
        carRepository.save(new CarEntity(1L,"gol", "1999", "branco", PICKUP) as Iterable)

        when:('I send car for update information')

        def car = new Car("fiat", "1999", "preto", PICKUP)

        HttpHeaders headers = new HttpHeaders()
        headers.setContentType(MediaType.APPLICATION_JSON)
        HttpEntity<Car> entity = new HttpEntity<Car>(car, headers)

        def response = restTemplate.exchange('/car/1', HttpMethod.PUT, entity, String.class)

        then:('I show the status no content')
        response.statusCode == HttpStatus.NO_CONTENT
    }

    @Test
    def 'should return a bad request state when try to update a car that does not exist'() {
        given:('I do not have a car registed')

        when:('I send car for update information')

        def carBodyModel = new Car("fiat", "1999", "preto", PICKUP)

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Car> entity = new HttpEntity<Car>(carBodyModel, headers);
        def response = restTemplate.exchange('/car/223', HttpMethod.PUT, entity, String.class)

        then:('I show the status bad reuquest')
        response.statusCode == HttpStatus.BAD_REQUEST
    }



}

