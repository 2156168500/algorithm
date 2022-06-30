package com.fjh.graph;

public class Test {
    public static void main(String[] args) {
        Graph graph = new Graph();
        int [][]arr = {{10,1,2},{20,1,3},{30,1,4},{40,2,3},{50,2,5},{60,3,5}};
        graph.createGraph(arr);
        graph.BFS(1);
        System.out.println("___________________________________");
        graph.DFS(1);
    }
}
