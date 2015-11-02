package vaeke.restcountries.functional

import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import vaeke.restcountries.test.support.assertions.HttpAssertionsTrait
import vaeke.restcountries.test.support.spring.AbstractSpringV1ApiWebIntegrationSpec

/**
 * Created by gandrianakis on 30/10/2015.
 */
@ActiveProfiles("test")
class ErrorHandlingSpec extends AbstractSpringV1ApiWebIntegrationSpec implements HttpAssertionsTrait {

    def "end point that throws exception should return empty HTTP 500"() {
        when:
            def response = apiClient.get(path: '')

        then:
            assertHttpStatus(response, HttpStatus.INTERNAL_SERVER_ERROR)
            assertEmptyResponse(response)
    }

    def "end point that doesn't exist should result empty 404"() {
        when:
            def response = apiClient.get(path: 'someNoneExistent')

        then:
            assertHttpNotFound(response)
            assertEmptyResponse(response)
    }
}
