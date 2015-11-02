package vaeke.restcountries.test.support.spring

import groovyx.net.http.HttpResponseException
import groovyx.net.http.RESTClient
import org.apache.http.client.ClientProtocolException
import spock.lang.Shared

import javax.annotation.PostConstruct

/**
 * Created by gandrianakis on 30/10/2015.
 */
abstract class AbstractSpringApiWebIntegrationSpec extends AbstractSpringWebIntegrationSpec {

    @Shared
    RESTClient apiClient

    @PostConstruct
    private void springInit() {
        apiClient = new RESTClient("http://localhost:${serverPort}${apiPath}${testSpecificPath}/") {

            /**
             * Don't throw an Exception on Error, simply return the response
             * This makes writing test code more elegant
             */
            @Override
            Object get(Map<String, ?> args) throws ClientProtocolException, IOException, URISyntaxException {
                try {
                    return super.get(args)
                }
                catch (HttpResponseException e) {
                    return e.response
                }
            }
        }

    }

    protected abstract String getTestSpecificPath();
}
