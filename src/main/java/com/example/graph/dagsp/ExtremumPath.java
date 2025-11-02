package com.example.graph.dagsp;

import com.example.graph.Edge;
import com.example.graph.Graph;

import java.util.Arrays;
import java.util.List;

public class ExtremumPath {
    public static int[] path(Graph graph, List<Integer> order, int source, String mode) {
        int n = graph.size();
        int[] dist = new int[n];
        boolean longest = "max".equalsIgnoreCase(mode);

        // Initializing distances
        if (longest) {
            Arrays.fill(dist, Integer.MIN_VALUE);
            dist[source] = 0;
        } else {
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[source] = 0;
        }

        // Relaxing edges in topological order
        for (int u : order) {
            if ((longest && dist[u] != Integer.MIN_VALUE) || (!longest && dist[u] != Integer.MAX_VALUE)) {
                for (Edge edge : graph.getNext(u)) {
                    int v = edge.to;
                    int w = edge.weight;

                    if (longest) {
                        // Longest path
                        if (dist[u] + w > dist[v]) {
                            dist[v] = dist[u] + w;
                        }
                    } else {
                        // Shortest path
                        if (dist[u] + w < dist[v]) {
                            dist[v] = dist[u] + w;
                        }
                    }
                }
            }
        }

        return dist;
    }
}
