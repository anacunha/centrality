package com.anacunha;

import com.anacunha.facebook.FacebookConnector;
import com.anacunha.graph.Graph;
import com.anacunha.graph.GraphReader;
import com.anacunha.metrics.Centrality;
import com.anacunha.util.MapUtil;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        int task;

        if (args.length > 1) {
            try {
                task = Integer.parseInt(args[0]);

                switch (task) {
                    case 1:
                        taskOne(args[1]);
                        break;
                    case 2:
                        taskTwo(args[1]);
                        break;
                    default:
                        System.out.println("Task number should be between 1 and 2");
                        break;
                }
            }
            catch (NumberFormatException e) {
                System.err.println("Argument " + args[0] + " must be an integer.");
                System.exit(1);
            }
        }
        else {
            System.out.println("<task_number> <task_input>");
        }
    }

    private static void taskOne(String fileName) {
        Graph<Integer> g = GraphReader.readGraph(fileName);

        System.out.println("Rank of Vertices by their Closeness\n");
        System.out.format("%-8s%s\n", "Vertex ", "Closeness");

        Centrality<Integer> centrality = new Centrality<>();

        for (Map.Entry<Integer, Double> entry : MapUtil.sortByValue(centrality.getCloseness(g)).entrySet()) {
            Integer vertex = entry.getKey();
            Double closeness = entry.getValue();
            System.out.format("%4d%5s%f\n", vertex, "", closeness);
        }
    }

    private static void taskTwo(String accessToken) {
        FacebookConnector.facebookCentrality(accessToken);
    }
}
