package com.fjh.graph;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class Union {
//    Key 某个节点 value 某个节点的上一个节点
    public HashMap<Node,Node> fatherMap;
    public HashMap<Node,Integer> sizeMap;
    public Union(List<Node>nodes){
        fatherMap = new HashMap<>();
        sizeMap = new HashMap<>();
        for(Node node:nodes){
            fatherMap.put(node,node);
            sizeMap.put(node,1);
        }
    }
    private Node findFather(Node node){
        Stack<Node>stack = new Stack<>();
        while (node != fatherMap.get(node)){
            stack.push(node);
            node = fatherMap.get(node);
        }
        while (!stack.isEmpty()){
            Node cur = stack.pop();
            fatherMap.put(cur,node);
        }
        return node;
    }
    public boolean isSameUnion(Node node1,Node node2){
        return findFather(node1) == findFather(node2);
    }
    public void union(Node node1,Node node2){
        if(node1 == null || node2 == null){
            return ;
        }
        Node father1 = findFather(node1);
        Node father2 = findFather(node2);
        if(father1 != father2){
            int size1 = sizeMap.get(father1);
            int size2 = sizeMap.get(father2);
            if(size1 >= size2){
                fatherMap.put(father2,father1);
                sizeMap.put(father1,size2 + size1);
                sizeMap.remove(father2);
            }else{
                fatherMap.put(father1,father2);
                sizeMap.put(father2,size2 + size1);
                sizeMap.remove(father1);
            }
        }
    }
}
