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

        graph.addConnection("a", "b");
        assertEquals(2, graph.size());

        try {
            graph.addConnection(null, "c");
            assertFalse("failed to generate IllegalArgumentException", true);
        } catch (IllegalArgumentException e){
            assertEquals("origin is null", e.getMessage());
        }
        assertEquals(2, graph.size());

        try {
            graph.addConnection("", "c");
            assertFalse("failed to generate IllegalArgumentException", true);
        } catch (IllegalArgumentException e){
            assertEquals("origin is empty", e.getMessage());
        }

        try {
            graph.addConnection("d", null);
            assertFalse("failed to generate IllegalArgumentException", true);
        } catch (IllegalArgumentException e){
            assertEquals("destination is null", e.getMessage());
        }
        assertEquals(2, graph.size());

        try {
            graph.addConnection("d", "");
            assertFalse("failed to generate IllegalArgumentException", true);
        } catch (IllegalArgumentException e){
            assertEquals("destination is empty", e.getMessage());
        }

        try {
            graph.addConnection("e", "e");
            assertFalse("failed to generate IllegalArgumentException", true);
        } catch (IllegalArgumentException e){
            assertEquals("destination is the same as origin", e.getMessage());
        }

        assertEquals(2, graph.size());
    }

    @Test
    public void isConnected1() {

        AdjacencyListGraph graph = new AdjacencyListGraph();

        graph.addConnection("a", "b");
        graph.addConnection("b", "c");

        try {
            graph.isConnected(null, "c");
            assertFalse("failed to generate IllegalArgumentException", true);
        } catch (IllegalArgumentException e){
            assertEquals("origin is null", e.getMessage());
        }
        assertEquals(3, graph.size());

        try {
            graph.isConnected("", "c");
            assertFalse("failed to generate IllegalArgumentException", true);
        } catch (IllegalArgumentException e){
            assertEquals("origin is empty", e.getMessage());
        }

        try {
            graph.isConnected("d", null);
            assertFalse("failed to generate IllegalArgumentException", true);
        } catch (IllegalArgumentException e){
            assertEquals("destination is null", e.getMessage());
        }

        try {
            graph.isConnected("d", "");
            assertFalse("failed to generate IllegalArgumentException", true);
        } catch (IllegalArgumentException e){
            assertEquals("destination is empty", e.getMessage());
        }

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

    @Test
    public void constructFromFile1() {

        AdjacencyListGraph graph = new AdjacencyListGraph("./test/data.txt");

        assertEquals(6, graph.size());
        assertEquals(true, graph.isConnected("a", "e"));
        assertEquals(false, graph.isConnected("a", "c"));

    }

    @Test
    public void constructFromFile2() {

        AdjacencyListGraph graph = new AdjacencyListGraph("./test/not-existed.txt");
        assertEquals(0, graph.size());

        assertFalse(graph.readCitiesGraphFromFile("./test/not-existed2.txt"));

        assertFalse(graph.readCitiesGraphFromFile("./test/empty.txt"));

    }
}