package com.example.graph;

import java.util.ArrayList;

public class Graph {
    private ArrayList<ArrayList<Edge>> graph;

    public Graph(int n) {
        graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public int size() {
        return graph.size();
    }

    public void addEdge(int from, int to, int weight) {
        graph.get(from).add(new Edge(to, weight));
    }

    public ArrayList<Edge> getNext(int from) {
        return graph.get(from);
    }
}
