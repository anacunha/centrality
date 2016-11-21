package com.anacunha.metrics;

import com.anacunha.graph.Graph;
import com.anacunha.graph.GraphReader;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CentralityTest {

    private Graph graph;

    @Before
    public void setUp() {
        graph = new Graph();
        graph.addUndirectedEdge(1, 2);
        graph.addUndirectedEdge(1, 3);
        graph.addUndirectedEdge(2, 3);
        graph.addUndirectedEdge(2, 5);
        graph.addUndirectedEdge(3, 4);
        graph.addUndirectedEdge(4, 6);
        graph.addUndirectedEdge(4, 7);
        graph.addUndirectedEdge(5, 6);
    }

    @Test
    public void testFarness() {
        Map<Integer, Integer> farness = new HashMap<>();
        farness.put(1, 12);
        farness.put(2, 10);
        farness.put(3, 9);
        farness.put(4, 9);
        farness.put(5, 11);
        farness.put(6, 11);
        farness.put(7, 14);
        assertThat(Centrality.getFarness(graph), is(farness));
    }

    @Test
    public void testCloseness() {
        Map<Integer, Double> closeness = new HashMap<>();
        closeness.put(1, (1.0 / 12));
        closeness.put(2, (1.0 / 10));
        closeness.put(3, (1.0 / 9));
        closeness.put(4, (1.0 / 9));
        closeness.put(5, (1.0 / 11));
        closeness.put(6, (1.0 / 11));
        closeness.put(7, (1.0 / 14));
        assertThat(Centrality.getCloseness(graph), is(closeness));
    }

    @Test
    public void testNormalizedCloseness() {
        graph = GraphReader.readGraph("res/edges_test_2");
        double maxFarness = 1.0 / (graph.getSize() - 1);
        Map<Integer, Double> normalizedCloseness = new HashMap<>();
        normalizedCloseness.put(1, (1.0 / 9) / maxFarness);
        normalizedCloseness.put(2, (1.0 / 9) / maxFarness);
        normalizedCloseness.put(3, (1.0 / 9) / maxFarness);
        normalizedCloseness.put(4, (1.0 / 9) / maxFarness);
        normalizedCloseness.put(5, (1.0 / 9) / maxFarness);
        normalizedCloseness.put(6, (1.0 / 5) / maxFarness);
        assertThat(Centrality.getNormalizedCloseness(graph), is(normalizedCloseness));
    }
}
