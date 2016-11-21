package com.anacunha.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph {

    private Map<Integer, Set<Integer>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    private void addVertex(Integer v1) {
        if (!adjacencyList.containsKey(v1)) {
            adjacencyList.put(v1, new HashSet<>());
        }
    }

    private void addDirectedEdge(Integer v1, Integer v2) {
        addVertex(v1);
        adjacencyList.get(v1).add(v2);
    }

    public void addUndirectedEdge(Integer v1, Integer v2) {
        addDirectedEdge(v1, v2);
        addDirectedEdge(v2, v1);
    }

    public Iterable<Integer> getNeighbors(Integer vertex) {
        if (adjacencyList.containsKey(vertex)) {
            return adjacencyList.get(vertex);
        }
        return null;
    }

    public Iterable<Integer> getVertices() {
        return adjacencyList.keySet();
    }
}
