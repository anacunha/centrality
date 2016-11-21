package com.anacunha.metrics;

import com.anacunha.graph.Graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Centrality<T> {

    /**
     * Returns the distances (shortest paths) from a given vertex v
     * to to every other vertex in Graph g.
     *
     * @param g The Graph g containing the given vertex v.
     * @param v The vertex v from where to calculate the distances.
     * @return The distances from each vertex in g to the vertex v.
     */
    private Map<T, Integer> getDistances(Graph<T> g, T v) {
        Map<T, Integer> distances = new HashMap<>();
        Queue<T> queue = new LinkedList<>();
        queue.add(v);

        for (T vertex : g.getVertices())
            distances.put(vertex, Integer.MAX_VALUE);
        distances.put(v, 0);

        while (!queue.isEmpty()) {
            T current = queue.remove();
            for (T neighbor : g.getNeighbors(current)) {
                if (distances.get(neighbor).equals(Integer.MAX_VALUE)) {
                    distances.put(neighbor, distances.get(current) + 1);
                    queue.add(neighbor);
                }
            }
        }
        return distances;
    }

    private int getFarness(Graph<T> g, T v) {
        int farness = 0;
        int vertices = g.getSize();

        // The farness of a given vertex v is the sum of all distances
        // from each vertex to v.
        for (Map.Entry<T, Integer> entry : getDistances(g, v).entrySet()) {
            Integer distance = entry.getValue();

            // If there is no directed path between vertices, then the total
            // number of vertices is used instead of the path length.
            if (distance.equals(Integer.MAX_VALUE))
                farness += vertices;
            else
                farness += distance;
        }
        return farness;
    }

    public Map<T, Integer> getFarness(Graph<T> g) {
        Map<T, Integer> farness = new HashMap<>();
        for (T vertex : g.getVertices()) {
            farness.put(vertex, getFarness(g, vertex));
        }

        return farness;
    }

    public Map<T, Double> getCloseness(Graph<T> g) {
        Map<T, Double> closeness = new HashMap<>();
        for (T vertex : g.getVertices()) {
            closeness.put(vertex, (1.0 / getFarness(g, vertex)));
        }

        return closeness;
    }

    public Map<T, Double> getNormalizedCloseness(Graph<T> g) {
        Map<T, Double> closeness = new HashMap<>();
        double maxFarness = 1.0 / (g.getSize() - 1);
        for (T vertex : g.getVertices()) {
            closeness.put(vertex, (1.0 / getFarness(g, vertex)) / maxFarness);
        }

        return closeness;
    }
}
