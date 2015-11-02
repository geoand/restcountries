package vaeke.restcountries.mvcsupport;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by gandrianakis on 29/10/2015.
 *
 * Allows Spring MVC to send an empty HTTP response with appropriate status code when an error occurs
 */
@Controller
@RequestMapping("${error.path:/error}")
public class CustomErrorController implements ErrorController {

    @Value("${error.path:/error}")
    private String errorPath;

    @Override
    public String getErrorPath() {
        return this.errorPath;
    }

    @RequestMapping
    @ResponseBody
    public ResponseEntity<Object> error(HttpServletRequest request) {
        return new ResponseEntity<>(getStatus(request));
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        final Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return HttpStatus.valueOf(statusCode);
    }
}
