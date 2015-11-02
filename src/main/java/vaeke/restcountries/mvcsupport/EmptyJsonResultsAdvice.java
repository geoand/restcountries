package vaeke.restcountries.mvcsupport;

import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import vaeke.restcountries.domain.ErrorResponse;

import java.util.List;

/**
 * Created by gandrianakis on 30/10/2015.
 *
 * When a controller method corresponding to a REST call returns a response that is equivalent to empty,
 * then HTTP 404 is returned
 */
@ControllerAdvice(annotations = RestControllerWithEmptyHandling.class)
public class EmptyJsonResultsAdvice implements ResponseBodyAdvice {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(EmptyJsonResultsAdvice.class);

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        if((null == body) || ( (body instanceof List) && (0 == ((List)body).size()) ) ) {
            LOG.debug("Empty response detected. Will result HTTP 404 along with the corresponding ErrorResponse", body);
            return createNotFoundResponse(response);
        }

        return body;
    }

    private ErrorResponse createNotFoundResponse(ServerHttpResponse serverHttpResponse) {
        final HttpStatus notFoundStatus = HttpStatus.NOT_FOUND;

        serverHttpResponse.setStatusCode(notFoundStatus);

        return new ErrorResponse(notFoundStatus.value(), notFoundStatus.getReasonPhrase());
    }
}
