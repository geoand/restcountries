package vaeke.restcountries.test.support.spring
/**
 * Created by gandrianakis on 30/10/2015.
 */
abstract class AbstractSpringV1ApiWebIntegrationSpec extends AbstractSpringApiWebIntegrationSpec {

    @Override
    protected String getTestSpecificPath() {
        return "/v1"
    }
}
