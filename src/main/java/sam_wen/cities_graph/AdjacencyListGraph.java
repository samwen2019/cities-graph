package sam_wen.cities_graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implements CitiesService, Use HashMap and LinkedList to represent connected cities graph
 */
public class AdjacencyListGraph implements CitiesService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdjacencyListGraph.class);

    private HashMap<String, LinkedList<String>>  adjacencyListMap;

    /**
     * Default constructor
     */
    public AdjacencyListGraph() {
        adjacencyListMap = new HashMap<>();
    }

    /**
     * Add connection (bi-direction) between two nodes
     *
     * @param origin        starting node
     * @param destination   ending node
     * @return boolean      true if success, false if failed
     */
    @Override
    public boolean addConnection(String origin, String destination) {

        if (origin == null) {
            LOGGER.error("origin is null");
            return false;
        }

        origin = origin.trim();
        if (origin.length() == 0) {
            LOGGER.error("origin is empty");
            return false;
        }

        if (destination == null) {
            LOGGER.error("destination is null");
            return false;
        }

        destination = destination.trim();
        if (destination.length() == 0) {
            LOGGER.error("destination is empty");
            return false;
        }

        if (destination.equals(origin)) {
            LOGGER.error("destination is the same as origin");
            return false;
        }

        addOriginToDestination(origin, destination);
        addOriginToDestination(destination, origin);

        return true;
    }

    /**
     * Check if two nodes are connected
     *
     * @param origin        starting node
     * @param destination   ending node
     * @return boolean      true if connected, false if not connected
     */
    @Override
    public boolean isConnected(String origin, String destination) {

        if (origin == null) {
            LOGGER.error("origin is null");
            return false;
        }

        origin = origin.trim();
        if (origin.length() == 0) {
            LOGGER.error("origin is empty");
            return false;
        }

        if (destination == null) {
            LOGGER.error("destination is null");
            return false;
        }

        destination = destination.trim();
        if (destination.length() == 0) {
            LOGGER.error("destination is empty");
            return false;
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
                    queue.add(nextNode);
                }
            }
        }

        return false;
    }

    /**
     * get size
     * @return int - size of nodes
     */
    public int size() {
        return adjacencyListMap.size();
    }


    /**
     * Add single direction link from origin to destination in the graph
     *
     * @param origin        starting node
     * @param destination   ending node
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

}
