package sam_wen.cities_graph;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.HttpStatusCodeException;

@RestController
public class CitiesErrorHandler implements ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CitiesErrorHandler.class);

    private static final String PATH = "/error";

    /**
     * per requirement: "Any unexpected input should result in a ’no’ response"
     *
     * TODO:
     *  1) not all exceptions are caused by unexpected input
     *  2) response code need to be more specific
     *
     * @param response
     * @return
     */
    @GetMapping(value = PATH)
    public String handleError(HttpServletResponse response) {
        response.setStatus(400);
        LOGGER.error("/error condition");
        return "no";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

}
