package vaeke.restcountries.test.support.assertions

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType

/**
 * Created by gandrianakis on 30/10/2015.
 */
trait HttpAssertionsTrait {

    void assertHttpOk(def response) {
        assertHttpStatus(response, HttpStatus.OK)
    }

    void assertHttpNotFound(def response) {
        assertHttpStatus(response, HttpStatus.NOT_FOUND)
    }

    void assertHttpStatus(def response, HttpStatus expectedHttpStatus) {
        assert response.status == expectedHttpStatus.value()
    }

    void assertEmptyResponse(def response) {
        assert response.data == null
    }

    void assertResponseJson(def response) {
        final String contentType = response.headers['Content-Type'].toString()
        assert contentType.contains(MediaType.APPLICATION_JSON_VALUE)
        assert contentType.contains("UTF-8")
    }

    void assertBodyIsNotEmptyList(def response) {
        final def responseData = response.data
        assert responseData instanceof List
        assert responseData.size() > 0
    }

    void assertHeaderContainsValue(def response, String headerName, String containedValue) {
        response.headers[headerName].toString().contains(containedValue)
    }

    void assertCustomEmptyResponse(def response) {
        def errorResponse = response.data
        errorResponse.status == 404
        errorResponse.message == "Not Found"
    }

}