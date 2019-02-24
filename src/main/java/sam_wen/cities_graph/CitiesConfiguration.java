package sam_wen.cities_graph;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.File;

/**
 * Provides configurations
 */
@Configuration
@EnableSwagger2
public class CitiesConfiguration {

    @Value("${datafile}")
    private String datafile;

    @Bean
    CitiesService citiesService() {
        AdjacencyListGraph graph = new AdjacencyListGraph();
        ReadCitiesServiceFromFile.read(graph, new File(datafile));
        return graph;
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/connected**"))
                .build();
    }
}
