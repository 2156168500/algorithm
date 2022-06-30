package com.fjh.tree;

import java.util.LinkedList;
import java.util.Queue;

public class MaxLevel {
    public int maxLevel(TreeNode root){
        if(root == null){
            return 0;
        }
        Queue<TreeNode>queue = new LinkedList<>();
        queue.add(root);
        int max = 0;
        int cur = 0;
        TreeNode curEnd = root;
        TreeNode nextEnd = null;
        while (!queue.isEmpty()){
            TreeNode curNode = queue.poll();
            cur++;
            if(curNode.left != null){
                queue.add(curNode.left);
                nextEnd = curNode.left;
            }
            if(curNode.right != null){
                queue.add(curNode.right);
                nextEnd = curEnd.right;
            }
            if(curEnd == curNode){
                curEnd = nextEnd;
                nextEnd = null;
                max = Math.max(max,cur);
                cur = 0;
            }
        }
        return max;
    }
}
