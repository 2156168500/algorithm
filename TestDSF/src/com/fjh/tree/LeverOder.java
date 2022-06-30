package com.fjh.tree;

import java.util.*;

public class LeverOder {
    public List<Integer>leverOder(TreeNode head){
        Queue<TreeNode>queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        queue.add(head);
        list.add(head.val);
        while (!queue.isEmpty()){
            head = queue.poll();
            if(head.left != null){
                queue.add(head.left);
                list.add(head.left.val);
            }
            if(head.right != null) {
                queue.add(head.right);
                list.add(head.right.val);
            }
        }
        return list;
    }
}
