package com.example.util;

import com.example.graph.Graph;
import com.example.graph.Edge;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DAGBuilder {
    public static Graph buildDAG(Graph graph, List<List<Integer>> sccs) {
        int sccCount = sccs.size();
        Graph dag = new Graph(sccCount);

        // Mapping each node to its SCC index
        int[] componentOf = new int[graph.size()];
        for (int i = 0; i < sccs.size(); i++) {
            for (int node : sccs.get(i)) {
                componentOf[node] = i;
            }
        }

        // Tracking edges between SCCs to avoid duplicates
        Set<String> seen = new HashSet<>();

        for (int u = 0; u < graph.size(); u++) {
            for (Edge e : graph.getNext(u)) {
                int from = componentOf[u];
                int to = componentOf[e.to];
                if (from != to) {
                    String key = from + "-" + to;
                    if (!seen.contains(key)) {
                        dag.addEdge(from, to, e.weight);
                        seen.add(key);
                    }
                }
            }
        }

        return dag;
    }
}
