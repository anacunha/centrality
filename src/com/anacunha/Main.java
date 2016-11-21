package com.anacunha;

import com.anacunha.graph.Graph;
import com.anacunha.graph.GraphReader;
import com.anacunha.metrics.Centrality;
import com.anacunha.util.MapUtil;

import java.util.Map;

public class Main {

    public static void main(String[] args) {

        if (args.length > 0) {
            Graph g = GraphReader.readGraph(args[0]);

            System.out.println("Rank of Vertices by their Closeness");
            System.out.format("\n%-8s%s", "Vertex ", "Closeness ");

            for (Map.Entry<Integer, Double> entry : MapUtil.sortByValue(Centrality.getCloseness(g)).entrySet()) {
                Integer vertex = entry.getKey();
                Double closeness = entry.getValue();
                System.out.format("\n%4d%5s%f", vertex, "", closeness);
            }
        }
    }
}
