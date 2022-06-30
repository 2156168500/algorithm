package com.fjh.tree;

import org.omg.CORBA.TRANSACTION_MODE;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class postOder {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> stack1 = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            stack1.push(node.val);
            if(node.left != null){
                stack.push(node.left);
            }
            if(node.right != null){
                stack.push(node.right);
            }
        }
        while (!stack1.isEmpty()){
            list.add(stack1.pop());
        }
        return list;
    }
}
