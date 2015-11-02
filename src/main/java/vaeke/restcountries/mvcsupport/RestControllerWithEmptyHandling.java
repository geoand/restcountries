package vaeke.restcountries.mvcsupport;

import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by gandrianakis on 30/10/2015.
 */
@RestController
@Retention(RetentionPolicy.RUNTIME)
public @interface RestControllerWithEmptyHandling {
}
