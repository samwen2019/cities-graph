package sam_wen.cities_graph;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Standard java boot application
 */
@SpringBootApplication
public class CitiesApplication {

    public static ApplicationContext appCtx;

    public static void main(String[] args) {
        appCtx = SpringApplication.run(CitiesApplication.class, args);
    }
}
