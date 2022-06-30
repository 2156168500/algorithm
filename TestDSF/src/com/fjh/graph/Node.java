package com.fjh.graph;

import java.util.ArrayList;

public class Node {
    public Integer val;
    public Integer in;
    public Integer out;
    public ArrayList<Node>nodes;
    public ArrayList<Edge>edges;
    public Node(Integer val){
        this.val = val;
        this.in = 0;
        this.out = 0;
        this.nodes = new ArrayList<>();
        this.edges = new ArrayList<>();
    }
}
