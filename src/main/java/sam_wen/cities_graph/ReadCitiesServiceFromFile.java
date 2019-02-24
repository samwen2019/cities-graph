package sam_wen.cities_graph;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Read an AdjacencyListGraph instance from file
 */
public class ReadCitiesServiceFromFile {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadCitiesServiceFromFile.class);

    /**
     * @param graph      a CitiesService instance
     * @param filepath   absolution or relative path
     * @return boolean   true if no error;
     *                   false if there is any error, but it will read the rest
     */
    public static boolean read(CitiesService graph, String filepath) {

        boolean ok = true;

        try {

            File file = new File(filepath);
            InputStreamReader is = new InputStreamReader(new FileInputStream(file));
            BufferedReader reader = new BufferedReader(is);
            int n = 0, okCount = 0;

            while (true) {

                String line = reader.readLine();
                if (line == null) break;
                n++;

                String pairs[] = line.split(",");
                if (pairs.length != 2) {
                    ok = false;
                    LOGGER.error("line " + n + ": format invalid - " + line);
                    continue;
                }

                String node1 = pairs[0].trim();
                if (node1.length() == 0) {
                    ok = false;
                    LOGGER.error("line " + n + ": format invalid, the first string is empty - " + line);
                    continue;
                }

                String node2 = pairs[1].trim();
                if (node2.length() == 0) {
                    ok = false;
                    LOGGER.error("line " + n + ": format invalid, the second string is empty - " + line);
                    continue;
                }

                graph.addConnection(node1, node2);
                okCount++;
            }

            reader.close();
            is.close();

            LOGGER.trace("read OK lines = " + okCount + "; read Error lines = " + (n - okCount));

            if (okCount == 0) {
                ok = false;
                LOGGER.error("failed to read any data from " + filepath);
                return false;
            }

        } catch (IOException e) {
            ok = false;
            LOGGER.error(e.getMessage() + " filepath = " + filepath);
            //e.printStackTrace();
        }

        return ok;
    }
}
