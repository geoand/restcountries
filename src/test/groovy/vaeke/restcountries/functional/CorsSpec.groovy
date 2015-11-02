package vaeke.restcountries.functional

import vaeke.restcountries.test.support.assertions.HttpAssertionsTrait
import vaeke.restcountries.test.support.spring.AbstractSpringV1ApiWebIntegrationSpec

/**
 * Created by George Andrianakis on 31/10/2015.
 */
class CorsSpec extends AbstractSpringV1ApiWebIntegrationSpec implements HttpAssertionsTrait {


    static final String ORIGIN = 'http://example.com'
    static final String ALLOWED_HEADERS = 'X-PINGOTHER'

    def "CORS pre-flight response contains expected HTTP headers"() {
        when:
            def response = apiClient.options(path: 'all',
                    headers: ['Access-Control-Request-Method': 'GET',
                              'Access-Control-Request-Headers': ALLOWED_HEADERS,
                              'Origin': ORIGIN]
            )

        then:
            assertHeaderContainsValue(response, 'Access-Control-Allow-Origin', ORIGIN)
            assertHeaderContainsValue(response, 'Access-Control-Allow-Methods', 'GET')
            assertHeaderContainsValue(response, 'Access-Control-Allow-Headers', ALLOWED_HEADERS)
    }
}
