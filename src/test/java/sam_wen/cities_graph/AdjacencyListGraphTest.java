package sam_wen.cities_graph;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AdjacencyListGraphTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addConnection() {

        AdjacencyListGraph graph = new AdjacencyListGraph();

        assertTrue(graph.addConnection("a", "b"));
        assertEquals(2, graph.size());

        assertFalse(graph.addConnection(null, "c"));
        assertFalse(graph.addConnection("", "c"));
        assertFalse(graph.addConnection("c", null));
        assertFalse(graph.addConnection("c", ""));
        assertFalse(graph.addConnection("e", "e"));

        assertEquals(2, graph.size());
    }

    @Test
    public void isConnected1() {

        AdjacencyListGraph graph = new AdjacencyListGraph();

        graph.addConnection("a", "b");
        graph.addConnection("b", "c");

        assertFalse(graph.isConnected(null, "c"));
        assertFalse(graph.isConnected("", "c"));
        assertFalse(graph.isConnected("d", null));
        assertFalse(graph.isConnected("d", ""));

        assertEquals(3, graph.size());
    }


    @Test
    public void isConnected2() {

        AdjacencyListGraph graph = new AdjacencyListGraph();

        graph.addConnection("a", "b");
        graph.addConnection("b", "c");

        assertEquals(true, graph.isConnected("a", "c"));
        assertEquals(false, graph.isConnected("a", "e"));

        assertEquals(true, graph.isConnected("e", "e"));

    }

    @Test
    public void size() {

        AdjacencyListGraph graph = new AdjacencyListGraph();

        assertEquals(0, graph.size());

        graph.addConnection("a", "b");
        graph.addConnection("b", "c");

        assertEquals(3, graph.size());
    }
}