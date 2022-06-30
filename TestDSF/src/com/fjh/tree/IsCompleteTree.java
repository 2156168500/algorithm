package com.fjh.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树的 root ，确定它是否是一个 完全二叉树 。
 *
 * 在一个 完全二叉树 中，除了最后一个关卡外，所有关卡都是完全被填满的，并且最后一个关卡中的所有节点都是尽可能靠左的。它可以包含 1 到 2h 节点之间的最后一级 h 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/check-completeness-of-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IsCompleteTree {
    public boolean isCompleteTree(TreeNode root) {
        if(root == null){
            return true;
        }
        boolean leaf = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (! queue.isEmpty()){
            root = queue.poll();
            TreeNode l = root.left;
            TreeNode r = root.right;
            if(leaf &&( (r != null || l != null)|| (l == null&&  r!= null))){
                return false;
            }
            if(r == null || l == null){
                leaf = true;
            }
            if(l != null){
                queue.add(l);
            }
            if(r != null){
                queue.add(r);
            }
        }
        return true;
    }
    public static class Info{
        public boolean isFull;
        public boolean isCom;
        public int height;
        public Info(boolean isFull,boolean isCom,int height){
            this.isFull = isFull;
            this.isCom = isCom;
            this.height = height;
        }
    }
    public boolean isCompleteTree2(TreeNode root){
        return  process(root).isCom;
    }
    public Info process(TreeNode node){
        if(node == null){
            return new Info(true,true,0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isCom = false;
        if(leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height){
            isCom = true;
        }else if(leftInfo.isFull && rightInfo.isCom && leftInfo.height == rightInfo.height){
            isCom = true;
        }else if(leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height +1){
            isCom = true;
        }else if(leftInfo.isCom && rightInfo.isFull && leftInfo.height ==rightInfo.height + 1){
            isCom = true;
        }
        return new Info(isFull,isCom,height);
    }

}
