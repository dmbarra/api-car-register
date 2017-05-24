import com.car.CarsApi
import com.car.models.CarBodyModel
import com.car.models.EnunCarCategory
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(classes = CarsApi.class,
        loader = SpringBootContextLoader.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloWorldTest extends Specification {

    @Autowired
    TestRestTemplate restTemplate;

    def 'should be return Oi Henrique when request first resource '() {
        given:('My service is online')
        // TODO: print the field value to the standard output

        when:('I access the service')
          def entity = restTemplate.getForEntity('/', String.class)

        then:('I show message: Oi Henrique!')
          entity.statusCode == HttpStatus.OK
          entity.body == 'Oi Henrique'
    }

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
        def response = restTemplate.postForEntity('/car', entity, String.class)

        then:('I show the status created and id of data base')
        response.statusCode == HttpStatus.CREATED
        response.body != null

    }

}

