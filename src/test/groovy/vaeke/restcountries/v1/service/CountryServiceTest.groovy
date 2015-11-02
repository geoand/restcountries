package vaeke.restcountries.v1.service

import spock.lang.Specification
import vaeke.restcountries.v1.domain.Country

import static org.assertj.core.api.Assertions.assertThat

/**
 * Created by George Andrianakis on 31/10/2015.
 */
class CountryServiceTest extends Specification {

    final CountryService countryService = new JsonFileCountryService()

    def "getAll returns non-empty country list"() {
        when:
            final List<Country> countries = countryService.all

        then:
            assertThat(countries).isNotEmpty()
    }

    def "getByAlpha returns country when called with 2-letter code"() {
        when:
            final Country country = countryService.getByAlpha("CO")

        then:
            country
            country.cca2 == "CO"
    }

    def "getByAlpha returns country when called with 3-letter code"() {
        when:
            final Country country = countryService.getByAlpha("COL")

        then:
            country
            country.cca2 == "CO"
    }

    def "getByCodeList returns country list"() {
        when:
            final List<Country> countries = countryService.getByCodeList(["CO", "NOR", "EE"])

        then:
            assertThat(countries*.cca2).containsExactly("CO", "NO", "EE")
    }

    def "getByCurrency returns country list"() {
        when:
            final List<Country> countries = countryService.getByCurrency("EUR")

        then:
            countries.each {country ->
                assertThat(country.currency).contains("EUR")
            }
    }

    def "getByName returns country list"() {
        when:
            final List<Country> countries = countryService.getByName("Norway", false)

        then:
            assertThat(countries).isNotEmpty()
            countries[0].name == "Norway"
    }

    def "getByName returns countries with priority"() {
        when:
            final List<Country> countries = countryService.getByName("United", false)

        then:
            assertThat(countries).isNotEmpty()
            countries[0].name == "United States Minor Outlying Islands"
    }

    def "getByName returns countries when called using alternate name"() {
        when:
            final List<Country> countries = countryService.getByName("Norge", false)

        then:
            assertThat(countries).isNotEmpty()
            countries[0].name == "Norway"
    }

    def "getByName returns countries when called using the full text option set if the full name is specified"() {
        when:
            final List<Country> countries = countryService.getByName("Russian Federation", true)

        then:
            assertThat(countries).isNotEmpty()
            countries[0].name == "Russia"
    }

    def "getByName returns an empty list when called using the full text option set if the full name is not specified"() {
        when:
            final List<Country> countries = countryService.getByName("Russian",  true)

        then:
            assertThat(countries).isEmpty()
    }

    def "getByCallingcode returns country list"() {
        when:
            final List<Country> countries = countryService.getByCallingcode("57")

        then:
            assertThat(countries.cca2).containsExactly("CO")
    }

    def "getByCapital returns country list"() {
        when:
            final List<Country> countries = countryService.getByCapital("Tallinn")

        then:
            assertThat(countries.cca2).containsExactly("EE")
            assertThat(countries.nativeName).containsExactly("Eesti")
    }

    def "getByRegion returns country list"() {
        when:
            final List<Country> countries = countryService.getByRegion("Europe")

        then:
            countries.each {country ->
                assertThat(country.region).contains("Europe")
            }
    }

    def "getBySubregion returns country list"() {
        when:
            final List<Country> countries = countryService.getBySubregion("Northern Europe")

        then:
            countries.each {country ->
                assertThat(country.subregion).contains("Northern Europe")
            }
    }

    def "getByLanguageCode returns country list"() {
        when:
            final List<Country> countries = countryService.getByLanguage("en")

        then:
            countries.each {country ->
                assertThat(country.languageCodes).contains("en")
            }
    }

}
