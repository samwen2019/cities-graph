package sam_wen.cities_graph;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Use HashMap and LinkedList to represent connected cities graph
 */
public class AdjacencyListGraph {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdjacencyListGraph.class);

    private HashMap<String, LinkedList<String>>  adjacencyListMap;

    /**
     * Default constructor
     */
    public AdjacencyListGraph() {
        adjacencyListMap = new HashMap<>();
    }


    /**
     * Constructor from a file
     */
    public AdjacencyListGraph(String filename) {
        this();
        readCitiesGraphFromFile(filename);
    }

    /**
     * Add connection (bi-direction) between two nodes
     *
     * @param origin
     * @param destination
     * @throws Exception
     */
    public void addConnection(String origin, String destination) throws IllegalArgumentException {

        if (origin == null) {
            throw new IllegalArgumentException("origin is null");
        }

        origin = origin.trim();
        if (origin.length() == 0) {
            throw new IllegalArgumentException("origin is empty");
        }

        if (destination == null) {
            throw new IllegalArgumentException("destination is null");
        }

        destination = destination.trim();
        if (destination.length() == 0) {
            throw new IllegalArgumentException("destination is empty");
        }

        if (destination.equals(origin)) {
            throw new IllegalArgumentException("destination is the same as origin");
        }

        addOriginToDestination(origin, destination);
        addOriginToDestination(destination, origin);

    }

    /**
     * Check if two nodes are connected
     *
     * @param origin
     * @param destination
     * @return
     */
    public boolean isConnected(String origin, String destination) throws IllegalArgumentException {

        if (origin == null) {
            throw new IllegalArgumentException("origin is null");
        }

        origin = origin.trim();
        if (origin.length() == 0) {
            throw new IllegalArgumentException("origin is empty");
        }

        if (destination == null) {
            throw new IllegalArgumentException("destination is null");
        }

        destination = destination.trim();
        if (destination.length() == 0) {
            throw new IllegalArgumentException("destination is empty");
        }

        if (destination.equals(origin)) {
            return true;
        }

        if (!adjacencyListMap.containsKey(origin) ||
                !adjacencyListMap.containsKey(destination)) {
            return false;
        }

        // set of nodes visited
        HashSet<String> visited = new HashSet<>();

        // BFS queue
        LinkedList<String> queue = new LinkedList<>();

        // BFS starts from origin node
        visited.add(origin);
        queue.add(origin);

        // BFS loop
        while (queue.size() != 0) {

            // pop out the first node from queue
            String node = queue.pop();

            // get adjacency nodes list
            LinkedList<String> adjacencyList = adjacencyListMap.get(node);

            // loop through the list
            //
            ListIterator<String> iterator = adjacencyList.listIterator();
            while (iterator.hasNext()) {

                String nextNode = iterator.next();

                // check if reaches the destination
                if (nextNode.equals(destination)) {
                    return true;
                }

                // not visited before, save it for late process
                if (!visited.contains(nextNode)) {
                    visited.add(nextNode);
                    queue.push(nextNode);
                }
            }
        }

        return false;
    }

    /**
     * to add single direction link from origin to destination in the graph
     *
     * @param origin
     * @param destination
     */
    private void addOriginToDestination(String origin, String destination) {

        LinkedList<String> adjacencyList = adjacencyListMap.get(origin);

        // created a linked list for the city if it is null
        if (adjacencyList == null) {

            adjacencyList = new LinkedList<String>();
            adjacencyListMap.put(origin, adjacencyList);

        } else {

            // do nothing if already existed
            if (adjacencyList.contains(destination)) {
                return;
            }
        }

        adjacencyList.add(destination);
    }

    /**
     * get size
     * @return
     */
    public int size() {
        return adjacencyListMap.size();
    }

    /**
     * @param filepath
     * @return
     */
    public boolean readCitiesGraphFromFile(String filepath) {

        try {

            File file = new File(filepath);
            InputStreamReader is = new InputStreamReader(new FileInputStream(file));
            BufferedReader reader = new BufferedReader(is);
            int n = 0;

            while (true) {

                String line = reader.readLine();
                if (line == null) break;
                n++;

                String pairs[] = line.split(",");
                if (pairs.length != 2) {
                    LOGGER.error("line " + n + ": format invalid - " + line);
                    continue;
                }

                String node1 = pairs[0].trim();
                if (node1.length() == 0) {
                    LOGGER.error("line " + n + ": format invalid, the first string is empty - " + line);
                    continue;
                }

                String node2 = pairs[1].trim();
                if (node2.length() == 0) {
                    LOGGER.error("line " + n + ": format invalid, the second string is empty - " + line);
                    continue;
                }

                addConnection(node1, node2);
            }

            reader.close();
            is.close();

            LOGGER.trace("graph size = " + size());

            if (size() == 0) {
                LOGGER.error("there is no city in the graph");
                return false;
            }

            return true;

        } catch (IOException e) {
            LOGGER.error(e.getMessage() + " filepath = " + filepath);
            //e.printStackTrace();
        }

        return false;
    }

}