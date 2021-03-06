package sam_wen.cities_graph;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Handle errors
 */
@RestController
public class CitiesErrorHandler implements ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CitiesErrorHandler.class);

    private static final String PATH = "/error";

    /**
     * per requirement: "Any unexpected input should result in a ’no’ response"
     *
     * TODO:
     *  1) not all exceptions are caused by unexpected input
     *  2) response code needs to be more specific
     *  3) error message needs to be more specific
     *
     * @param response  current HttpServletResponse object
     * @return          "no"
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
