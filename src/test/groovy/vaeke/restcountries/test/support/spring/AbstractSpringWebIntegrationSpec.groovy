package vaeke.restcountries.test.support.spring

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.SpringApplicationConfiguration
import org.springframework.boot.test.WebIntegrationTest
import spock.lang.Specification
import vaeke.restcountries.Application

/**
 * Created by gandrianakis on 30/10/2015.
 */
@SpringApplicationConfiguration(Application)
@WebIntegrationTest("server.port:0")
abstract class AbstractSpringWebIntegrationSpec extends Specification {

    @Value('${local.server.port}')
    int serverPort

    @Value('${api.path}')
    String apiPath


}
