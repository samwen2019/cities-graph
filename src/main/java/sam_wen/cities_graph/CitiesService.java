package sam_wen.cities_graph;

/**
 * Interface for cities connection service
 */
public interface CitiesService {

    /**
     * @param origin        original city
     * @param destination   destination city
     * @return              true if success, else false
     */
    boolean addConnection(String origin, String destination);

    /**
     * @param origin        original city
     * @param destination   destination city
     * @return              true if connected, else false
     */
    boolean isConnected(String origin, String destination);

} 