package sam_wen.cities_graph;

import org.junit.Test;

import static org.junit.Assert.*;

public class CitiesApplicationTest {

    @Test
    public void main() {

        String args[] = {"--datafile=test/city.txt"};
        CitiesApplication.main(args);
        assertNotNull(CitiesApplication.appCtx);

        AdjacencyListGraph graph = CitiesApplication.appCtx.getBean(AdjacencyListGraph.class);
        assertNotNull(graph);

        assertTrue(graph.size() > 0);

        assertTrue(graph.isConnected("Boston", "New York"));
    }
}