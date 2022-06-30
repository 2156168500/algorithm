package com.fjh.graph;

import java.util.*;

public class Graph {
    public HashMap<Integer,Node>nodeSet;
    public HashSet<Edge>edgeSet;
    public Graph(){
        nodeSet = new HashMap<>();
        edgeSet = new HashSet<>();
    }

    /**
     *
     * @param arr 图的边的信息,arr[i][0]边的权值,arr[i][1]from,arr[i][2]边的to
     */
    public void createGraph(int[][]arr){
        for(int i = 0; i < arr.length; i++){
            int weight = arr[i][0];
           int from = arr[i][1];
           int to = arr[i][2];
           if(!this.nodeSet.containsKey(from)){
               this.nodeSet.put(from,new Node(from));
           }
            if(!this.nodeSet.containsKey(to)){
                this.nodeSet.put(to,new Node(to));
            }
            Node fromNode = nodeSet.get(from);
            Node toNode = nodeSet.get(to);
            fromNode.out++;
            toNode.in++;
            fromNode.nodes.add(toNode);
            Edge newEdge = new Edge(weight,fromNode,toNode);
            fromNode.edges.add(newEdge);
            this.edgeSet.add(newEdge);
        }
    }
    public void BFS(int v){
        Node node = this.nodeSet.get(v);
        Queue<Node>queue = new LinkedList<>();
        HashSet<Node>hashSet = new HashSet<>();
        queue.add(node);
        hashSet.add(node);
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.println(cur.val);
            for (Node next:
                 cur.nodes ) {
                if(!hashSet.contains(next)){
                    queue.add(next);
                    hashSet.add(next);
                }
            }
        }
    }
    public void DFS(Integer v){
        Node node = this.nodeSet.get(v);
        Stack<Node> stack = new Stack<>();
        HashSet<Node> hashSet = new HashSet<>();
        stack.push(node);
        System.out.println(node.val);
        hashSet.add(node);
        while(!stack.isEmpty()){
            Node cur = stack.pop();
            for(Node next: cur.nodes){
                if(!hashSet.contains(next)){
                    hashSet.add(next);
                    stack.push(cur);
                    stack.push(next);
                    System.out.println(next.val);
                    break;
                }
            }
        }
    }
    public Set<Edge> kruskalMST(Node node){
        Union union = new Union(node.nodes);
        Set<Edge> set = new HashSet<>();
        PriorityQueue<Edge>priorityQueue = new PriorityQueue<>(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
              return o1.weight - o2.weight;
            }
        });
        for (Edge e:
           this. edgeSet) {
            priorityQueue.add(e);
        }
        while (!priorityQueue.isEmpty()){
            Edge edge = priorityQueue.poll();
            if(!union.isSameUnion(edge.from,edge.to)){
                set.add(edge);
                union.union(edge.from,edge.to);
            }
        }
        return set;
    }
}
