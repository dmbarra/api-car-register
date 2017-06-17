package component

import com.car.CarsApi
import com.car.models.CarBodyModel
import com.car.models.EnunCarCategory
import com.car.models.repository.CarRegister
import com.car.repositories.CarRepository
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

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
        def carBodyModel = CarBodyModel
                .builder()
                .collor("branco")
                .year("1999")
                .model("gol")
                .category(EnunCarCategory.PICKUP)
                .build()
                .toJson()

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(carBodyModel.toString(),headers);

        when:('I send new car information')
        def response = restTemplate.exchange('/car', HttpMethod.POST, entity, String.class)

        then:('I show the status created and id of data base')
        response.statusCode == HttpStatus.CREATED
        response.body != null

    }

    @Test
    def 'should return status no content when update register'() {
        given:('I have a car registed')
        def carRegister = new CarRegister("gol", "branco", "1999", EnunCarCategory.PICKUP);
        carRepository.save(carRegister);

        when:('I send car for update information')

        def carBodyModel = CarBodyModel
                .builder()
                .collor("preto")
                .year("1999")
                .model("fiat")
                .category(EnunCarCategory.PICKUP)
                .build()
                .toJson()

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(carBodyModel.toString(),headers);
        def response = restTemplate.exchange('/car/1', HttpMethod.PUT, entity, String.class)

        then:('I show the status no content')
        response.statusCode == HttpStatus.NO_CONTENT
    }

    @Test
    def 'should return a bad request state when try to update a car that does not exist'() {
        given:('I do not have a car registed')

        when:('I send car for update information')

        def carBodyModel = CarBodyModel
                .builder()
                .collor("preto")
                .year("1999")
                .model("fiat")
                .category(EnunCarCategory.PICKUP)
                .build()
                .toJson()

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(carBodyModel.toString(),headers);
        def response = restTemplate.exchange('/car/223', HttpMethod.PUT, entity, String.class)

        then:('I show the status bad reuquest')
        response.statusCode == HttpStatus.BAD_REQUEST
    }

}

