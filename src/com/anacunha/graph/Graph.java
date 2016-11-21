package com.anacunha.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph<T> {

    private Map<T, Set<T>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    private void addVertex(T v1) {
        if (!adjacencyList.containsKey(v1)) {
            adjacencyList.put(v1, new HashSet<>());
        }
    }

    private void addDirectedEdge(T v1, T v2) {
        addVertex(v1);
        adjacencyList.get(v1).add(v2);
    }

    public void addUndirectedEdge(T v1, T v2) {
        addDirectedEdge(v1, v2);
        addDirectedEdge(v2, v1);
    }

    public Iterable<T> getNeighbors(T vertex) {
        if (adjacencyList.containsKey(vertex)) {
            return adjacencyList.get(vertex);
        }
        return null;
    }

    public Iterable<T> getVertices() {
        return adjacencyList.keySet();
    }

    public int getSize() {
        return adjacencyList.size();
    }
}
