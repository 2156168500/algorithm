package com.fjh.union;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @author 21561
 */
public class UnionSet<V> {
    private HashMap<V,Node> nodeMap;
    private HashMap<Node,Node>fatherMap;
    private HashMap<Node,Integer>sizeMap;
    public UnionSet(List<V>nodes){
        for (V node:
             nodes) {
            Node newNode = new Node(node);
            nodeMap.put(node,newNode);
            fatherMap.put(newNode,newNode);
            sizeMap.put(newNode,1);
       }
    }
    private Node findFather(V x){
        Node node = nodeMap.get(x);
        Stack<Node>stack = new Stack<>();
        while (node != fatherMap.get(node)){
            stack.push(node);
            node = fatherMap.get(node);
        }
        while (!stack.isEmpty()){
            fatherMap.put(stack.pop(),node);
        }
        return node;
    }
    public  boolean isSameUnion(V a,V b){
        return  findFather(a) == findFather(b);
    }
    public void union(V a,V b){
        Node nodeA = findFather(a);
        Node nodeB = findFather(b);
        int sizeA = sizeMap.get(nodeA);
        int sizeB = sizeMap.get(nodeB);
        Node longNode = sizeA >= sizeB ? nodeA : nodeB;
        Node slowNode = longNode == nodeA ? nodeB : nodeA;
        fatherMap.put(slowNode,longNode);
        sizeMap.put(longNode,sizeA+sizeB);
        sizeMap.remove(slowNode);
    }
}
