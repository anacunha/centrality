package com.anacunha.graph;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class GraphReaderTest {

    private Graph expected;
    private Graph readGraph;

    @Before
    public void setUp() {
        expected = new Graph();
        expected.addUndirectedEdge(1, 2);
        expected.addUndirectedEdge(1, 3);
        expected.addUndirectedEdge(2, 3);
        expected.addUndirectedEdge(2, 5);
        expected.addUndirectedEdge(3, 4);
        expected.addUndirectedEdge(4, 6);
        expected.addUndirectedEdge(4, 7);
        expected.addUndirectedEdge(5, 6);

        readGraph = GraphReader.readGraph("res/edges_test");
    }

    @Test
    public void testReadGraphVertices() {
        assertThat(readGraph.getVertices(), is(expected.getVertices()));
    }

    @Test
    public void testReadGraphNeighbors() {
        for (Integer vertex : readGraph.getVertices()) {
            assertThat(readGraph.getNeighbors(vertex), is(expected.getNeighbors(vertex)));
        }
    }
}
