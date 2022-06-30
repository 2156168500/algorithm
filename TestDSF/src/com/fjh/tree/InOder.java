package com.fjh.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class InOder {
    public static List<Integer> inorderTraversal(TreeNode root){
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;

        while (!stack.isEmpty() || cur != null){
            if(cur != null){
                stack.push(cur);
                cur = cur.left;
            }else {
                cur = stack.pop();
               list.add(cur.val);
               cur = cur.right;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode();
        TreeNode node2 = new TreeNode();
        TreeNode node3 = new TreeNode();
        node1.right = node2;
        node2.left = node3;
        node1.val = 1;
        node2.val = 2;
        node3.val = 3;
        inorderTraversal(node1);
    }
}
