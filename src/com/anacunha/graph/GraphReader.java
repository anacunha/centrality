package com.anacunha.graph;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GraphReader {

    /**
     * Returns an undirected unweighted Graph from a file, where each
     * line consists of two vertex names separated by a single space,
     * representing an edge between those two nodes.
     *
     * @param fileName The filename of the file to be read.
     * @return The Graph from the given file.
     */
    public static Graph<Integer> readGraph(String fileName) {
        Graph<Integer> graph = new Graph<>();

        // Adds an undirected edge for each line in file
        try (Scanner scanner = new Scanner(new File(fileName))) {

            while (scanner.hasNext()){
                String[] edges = scanner.nextLine().split("\\s+");
                graph.addUndirectedEdge(Integer.valueOf(edges[0]), Integer.valueOf(edges[1]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return graph;
    }
}
