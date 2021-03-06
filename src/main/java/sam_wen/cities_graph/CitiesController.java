package sam_wen.cities_graph;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Restful API for checking if two cities are connected
 */
@RestController
public class CitiesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CitiesController.class);

    @Autowired
    CitiesService citiesService;

    /**
     * @param origin        origin city
     * @param destination   destination
     * @return 
     *          "yes" for connected;
     *          "no" for any other cases
     */
    @RequestMapping(value = "/connected", method = RequestMethod.GET)
    public String isConnected(
            @RequestParam(name = "origin") String origin,
            @RequestParam(name = "destination") String destination
    ) {
        if (citiesService.isConnected(origin, destination)) {
            return "yes";
        } else {
            return "no";
        }
    }
}
