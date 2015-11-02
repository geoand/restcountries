package vaeke.restcountries.test.support.assertions

/**
 * Created by gandrianakis on 30/10/2015.
 */
trait CountryAssertionsTrait {

    void assertCountryName(Object country, String expectedName) {
        assert country.name == expectedName
    }

    void assertAlpha2Code(Object country, String expectedCode) {
        assert country.alpha2Code == expectedCode
    }

    void assertAlpha3Code(Object country, String expectedCode) {
        assert country.alpha3Code == expectedCode
    }

}