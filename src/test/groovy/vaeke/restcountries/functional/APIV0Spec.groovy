package vaeke.restcountries.functional

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import vaeke.restcountries.test.support.assertions.CountryAssertionsTrait
import vaeke.restcountries.test.support.assertions.HttpAssertionsTrait
import vaeke.restcountries.test.support.spring.AbstractSpringV0ApiWebIntegrationSpec

import static org.assertj.core.api.Assertions.assertThat

/**
 * Created by gandrianakis on 30/10/2015.
 */
class APIV0Spec extends AbstractSpringV0ApiWebIntegrationSpec implements HttpAssertionsTrait, CountryAssertionsTrait {

    @Autowired
    private ObjectMapper objectMapper

    def "'/' path returns list of countries"() {
        when:
            def response = apiClient.get(path: '')

        then:
            assertHttpOk(response)
            assertResponseJson(response)
            assertBodyIsNotEmptyList(response)
    }

    def "'/all' path returns list of countries"() {
        when:
            def response = apiClient.get(path: 'all')

        then:
            assertHttpOk(response)
            assertResponseJson(response)

        and:
            assertBodyIsNotEmptyList(response)
    }

    def "'/alpha/co' returns the appropriate country data"() {
        when:
            def response = apiClient.get(path: 'alpha/CO')

        then:
            assertHttpOk(response)
            assertResponseJson(response)

        and:
            def country = getCountry(response)
            assertCountryName(country, "Colombia")
            assertAlpha2Code(country, "CO")
    }

    def "'/alpha2/co' returns the appropriate country data"() {
        when:
            def response = apiClient.get(path: 'alpha/CO')

        then:
            assertHttpOk(response)
            assertResponseJson(response)

        and:
            def country = getCountry(response)
            assertCountryName(country, "Colombia")
            assertAlpha2Code(country, "CO")
    }

    def "'/alpha/col' returns the appropriate country data"() {
        when:
            def response = apiClient.get(path: 'alpha/COL')

        then:
            assertHttpOk(response)
            assertResponseJson(response)

        and:
            def country = getCountry(response)
            assertCountryName(country, "Colombia")
            assertAlpha3Code(country, "COL")
    }

    def "'/alpha3/col' returns the appropriate country data"() {
        when:
            def response = apiClient.get(path: 'alpha/COL')

        then:
            assertHttpOk(response)
            assertResponseJson(response)

        and:
            def country = getCountry(response)
            assertCountryName(country, "Colombia")
            assertAlpha3Code(country, "COL")
    }

    def "'/alpha/zzz' returns HTTP 404"() {
        when:
            def response = apiClient.get(path: 'alpha/zzz')

        then:
            assertHttpNotFound(response)
            assertResponseJson(response)

        and:
            assertCustomEmptyResponse(response)
    }

    def "'/alpha?codes=ar;be;fr;it' multiple codes in query param handled correctly"() {
        when:
            def response = apiClient.get(path: 'alpha', query: [codes: 'ar;be;fr;it'])

        then:
            assertHttpOk(response)
            assertResponseJson(response)

        and:
            assertThat(response.data*.alpha2Code).containsExactly("AR", "BE", "FR", "IT")
    }


    def "'/currency/eur' returns appropriate currency data"() {
        when:
            def response = apiClient.get(path: 'currency/eur')

        then:
            assertHttpOk(response)
            assertResponseJson(response)

        and:
            assertBodyIsNotEmptyList(response)

        and:
            response.data.each { country ->
                assertThat(country.currencies).contains('EUR')
            }
    }

    def "'/currency/yyy' returns HTTP 404"() {
        when:
            def response = apiClient.get(path: 'currency/yyy')

        then:
            assertHttpNotFound(response)
            assertResponseJson(response)

        and:
            assertCustomEmptyResponse(response)
    }

    def "'/callingcode/1' returns appropriate country data"() {
        when:
            def response = apiClient.get(path: 'callingcode/1')

        then:
            assertHttpOk(response)
            assertResponseJson(response)

        and:
            assertBodyIsNotEmptyList(response)

        and:
            response.data.each { country ->
                assertThat(country.callingCodes).contains('1')
            }
    }

    def "'/callingcode/99999' returns HTTP 404"() {
        when:
            def response = apiClient.get(path: 'callingcode/99999')

        then:
            assertHttpNotFound(response)
            assertResponseJson(response)

        and:
            assertCustomEmptyResponse(response)
    }

    def "'/capital/washington' returns appropriate country data"() {
        when:
            def response = apiClient.get(path: 'capital/washington')

        then:
            assertHttpOk(response)
            assertResponseJson(response)

        and:
            assertBodyIsNotEmptyList(response)

        and:
            response.data.each { country ->
                country.capital.toLowerCase() == 'washington d.c.'
            }
    }

    def "'/capital/nonexistent' returns HTTP 404"() {
        when:
            def response = apiClient.get(path: 'capital/nonexistent')

        then:
            assertHttpNotFound(response)
            assertResponseJson(response)

        and:
            assertCustomEmptyResponse(response)
    }

    def "'/region/europe' returns appropriate country data"() {
        when:
            def response = apiClient.get(path: 'region/europe')

        then:
            assertHttpOk(response)
            assertResponseJson(response)

        and:
            assertBodyIsNotEmptyList(response)

        and:
            response.data.each { country ->
                country.region.toLowerCase() == 'europe'
            }
    }

    def "'/lang/en' returns appropriate country data"() {
        when:
            def response = apiClient.get(path: 'lang/en')

        then:
            assertHttpOk(response)
            assertResponseJson(response)

        and:
            assertBodyIsNotEmptyList(response)

        and:
            response.data.each { country ->
                assertThat(country.languages).contains('en')
            }
    }

    def "'/name/russia' returns the appropriate country data"() {
        when:
        def response = apiClient.get(path: 'name/russia')

        then:
            assertHttpOk(response)
            assertResponseJson(response)

        and:
            response.data.each { country ->
                assert country.name == 'Russia'
            }
    }

    def "borders of Colombia are correct"() {
        when:
            def response = apiClient.get(path: 'alpha/CO')

        then:
            assertHttpOk(response)
            assertResponseJson(response)

        and:
            assertThat(getCountry(response).borders).containsOnly("BRA", "ECU", "PAN", "PER", "VEN")
    }

    private Object getCountry(def response) {
        return response.data
    }


}
