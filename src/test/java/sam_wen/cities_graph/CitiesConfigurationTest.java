package sam_wen.cities_graph;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CitiesConfigurationTest {

    @Autowired
    AdjacencyListGraph graph;

    @Test
    public void adjacencyListGraph() {
        assertNotNull(graph);
        assertTrue(graph.size() > 0);
    }
}