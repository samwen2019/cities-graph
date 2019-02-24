package sam_wen.cities_graph;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Scanner;

/**
 * Read an AdjacencyListGraph instance from file
 */
public class ReadCitiesServiceFromFile {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadCitiesServiceFromFile.class);

    /**
     * @param graph      a CitiesService instance
     * @param file       File object handle
     * @return boolean   true if no error;
     *                   false if there is any error, but it will read the rest
     */
    public static boolean read(CitiesService graph, File file) {

        boolean ok = true;
        Scanner scanner = null;

        try {

            scanner = new Scanner(file);
            int n = 0, okCount = 0;

            while (scanner.hasNextLine()) {

                String line = scanner.nextLine();
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

                if (graph.addConnection(node1, node2)) {
                    okCount++;
                } else {
                    ok = false;
                }
            }

            LOGGER.trace("read OK lines = " + okCount + "; read Error lines = " + (n - okCount));

            if (okCount == 0) {
                ok = false;
                LOGGER.error("failed to read any data from " + file.getAbsolutePath());
            }

        } catch (IOException e) {

            ok = false;
            LOGGER.error(e.getMessage() + " filepath = " + file.getAbsolutePath());
            //e.printStackTrace();

        }

        if (scanner != null) {
            scanner.close();
        }

        return ok;
    }
}
