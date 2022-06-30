package com.fjh.tree;

import java.util.ArrayList;
import java.util.List;

class Node {
    public int val;
    public List<Node> childes = new ArrayList<>();
    public Node(int val,List<Node> childes){
        this.childes = childes;
    }
    public Node(){

    }
}

public class CodecMoreTree {
    public TreeNode code(Node root){
        if(root == null){
            return null;
        }
        TreeNode head = new TreeNode(root.val);
        head.left = en(root.childes);
        return head ;
    }
    private TreeNode en (List<Node>childes){
        if(childes == null){
            return null;
        }
        TreeNode ans =  null;
        TreeNode cur = null;
        for (Node node:
             childes) {
            TreeNode tNode  = new TreeNode(node.val);
            if(ans == null){
                ans = tNode;
            }else{
                cur.right = tNode;
            }
            cur = tNode;
            cur.left = en(node.childes);
        }
        return ans;
    }
    public Node decode(TreeNode root){
        if(root == null){
            return null;
        }
        Node head = new Node(root.val, de(root.left));
        return head;
    }
    private List<Node> de(TreeNode node){
        if(node == null){
            return  null;
        }
        List<Node> childes = new ArrayList<>();
        while (node != null){
            childes.add(new Node(node.val, de(node.left)));
            node = node.right;
        }
        return childes;
    }
}
