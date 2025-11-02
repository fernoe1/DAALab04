package com.example.graph.topo;

import com.example.graph.Edge;
import com.example.graph.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DFSVariant {
    public static List<Integer> topologicalSort(Graph dag) {
        int n = dag.size();
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            if (!visited[i]) dfs(dag, i, visited, stack);
        }

        List<Integer> order = new ArrayList<>();
        while (!stack.isEmpty()) {
            order.add(stack.pop());
        }

        return order;
    }

    private static void dfs(Graph dag, int from, boolean[] visited, Stack<Integer> stack) {
        visited[from] = true;

        for (Edge next : dag.getNext(from)) {
            if (!visited[next.to]) dfs(dag, next.to, visited, stack);
        }

        stack.push(from);
    }
}
