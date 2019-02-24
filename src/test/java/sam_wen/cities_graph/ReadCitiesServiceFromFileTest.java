package sam_wen.cities_graph;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.io.File;

public class ReadCitiesServiceFromFileTest {

    @Test
    public void read0() {
        assertNotNull(new ReadCitiesServiceFromFile());
    }

    @Test
    public void read1() {

        AdjacencyListGraph graph = new AdjacencyListGraph();
        assertEquals(0, graph.size());
        assertTrue(ReadCitiesServiceFromFile.read(graph, new File("test/city.txt")));
        assertEquals(6, graph.size());

        assertEquals(true, graph.isConnected("Boston", "Newark"));
        assertEquals(true, graph.isConnected("Boston", "Philadelphia"));
        assertEquals(false, graph.isConnected("Philadelphia", "Albany"));
    }

    @Test
    public void read2() {

        AdjacencyListGraph graph = new AdjacencyListGraph();
        assertEquals(0, graph.size());
        assertFalse(ReadCitiesServiceFromFile.read(graph, new File("./test/data.txt")));
        assertEquals(6, graph.size());

        assertEquals(true, graph.isConnected("a", "e"));
        assertEquals(false, graph.isConnected("a", "c"));
    }

    @Test
    public void read3() {

        AdjacencyListGraph graph = new AdjacencyListGraph();

        assertFalse(ReadCitiesServiceFromFile.read(graph, new File("not-existed.txt")));
        assertFalse(ReadCitiesServiceFromFile.read(graph, new File("./test/empty.txt")));

    }

    @Test
    public void read4() {

        AdjacencyListGraph graph = mock(AdjacencyListGraph.class, Mockito.RETURNS_DEEP_STUBS);

        assertNotNull(graph);

        BDDMockito.when(graph.addConnection(anyString(),anyString())).thenReturn(false);

        assertFalse(ReadCitiesServiceFromFile.read(graph, new File("test/city.txt")));

    }
}