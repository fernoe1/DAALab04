package com.example.graph.scc;

import com.example.graph.Edge;
import com.example.graph.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Kosaraju {
    public static List<List<Integer>> findSCCs(Graph graph) {
        int n = graph.size();
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();

        // Filling stack by finishing times
        for (int i = 0; i < n; i++) {
            if (!visited[i]) fillStack(graph, i, visited, stack);
        }

        // Building transpose
        Graph transpose = new Graph(n);
        for (int i = 0; i < n; i++) {
            for (Edge to : graph.getNext(i)) {
                transpose.addEdge(to.to, i, to.weight); // reverse direction
            }
        }

        // Processing  all vertices in order defined by stack
        Arrays.fill(visited, false);
        List<List<Integer>> sccs = new ArrayList<>();

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                List<Integer> component = new ArrayList<>();
                dfsTranspose(transpose, node, visited, component);
                sccs.add(component);
            }
        }

        return sccs;
    }

    private static void fillStack(Graph graph, int node, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for (Edge to : graph.getNext(node)) {
            if (!visited[to.to]) fillStack(graph, to.to, visited, stack);
        }
        stack.push(node);
    }

    private static void dfsTranspose(Graph transpose, int node, boolean[] visited, List<Integer> component) {
        visited[node] = true;
        component.add(node);

        for (Edge to : transpose.getNext(node)) {
            if (!visited[to.to]) dfsTranspose(transpose, to.to, visited, component);
        }
    }
}
