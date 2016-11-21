package com.anacunha.metrics;

import com.anacunha.graph.Graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Centrality {

    private static int getFarness(Graph g, Integer v) {
        Map<Integer, Integer> distances = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);

        for (Integer vertex : g.getVertices())
            distances.put(vertex, Integer.MAX_VALUE);
        distances.put(v, 0);

        int farness = 0;

        while (!queue.isEmpty()) {
            Integer current = queue.remove();
            for (Integer neighbor : g.getNeighbors(current)) {
                if (distances.get(neighbor).equals(Integer.MAX_VALUE)) {
                    distances.put(neighbor, distances.get(current) + 1);
                    farness += (distances.get(current) + 1);
                    queue.add(neighbor);
                }
            }
        }
        return farness;
    }

    public static Map<Integer, Integer> getFarness(Graph g) {
        Map<Integer, Integer> farness = new HashMap<>();
        for (Integer vertex : g.getVertices()) {
            farness.put(vertex, getFarness(g, vertex));
        }

        return farness;
    }

    public static Map<Integer, Double> getCloseness(Graph g) {
        Map<Integer, Double> closeness = new HashMap<>();
        for (Integer vertex : g.getVertices()) {
            closeness.put(vertex, (1.0 / getFarness(g, vertex)));
        }

        return closeness;
    }
}
