import com.car.CarsApi
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ContextConfiguration(classes = CarsApi.class, loader = SpringBootContextLoader.class)
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
}

