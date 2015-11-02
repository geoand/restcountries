package vaeke.restcountries.test.support.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import vaeke.restcountries.v1.domain.Country;
import vaeke.restcountries.v1.service.CountryService;
import vaeke.restcountries.v1.service.JsonFileCountryService;

import java.util.List;

/**
 * Created by gandrianakis on 30/10/2015.
 */
@Configuration
@Profile("test")
public class TestConfiguration {

    @Bean
    @Primary
    public CountryService countryService() {
        return new JsonFileCountryService() {
            @Override
            public List<Country> getAll() {
                throw new RuntimeException("dummy");
            }
        };
    }
}
