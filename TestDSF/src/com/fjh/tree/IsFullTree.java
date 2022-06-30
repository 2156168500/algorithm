package com.fjh.tree;

/**
 * 判断一颗二叉树是否是满二叉树
 */
public class IsFullTree {
    public static boolean isFullTree(TreeNode root){
        Info info = process(root);
        int height = info.height;
        int nodeSize = info.nodeSize;
        if(nodeSize == Math.pow(2,height) - 1){
            return  true;
        }
        return  false;
    }
    public static class Info{
        public int nodeSize;
        public int height;
        public Info(int nodeSize,int height){
            this.nodeSize = nodeSize;
            this.height = height;
        }
    }
    public static Info process(TreeNode node){
        if(node == null){
            return  new Info(0,0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo =process(node.right);
        int height = leftInfo.height + rightInfo.height +1;
        int nodes = leftInfo.nodeSize + rightInfo.nodeSize + 1;
        return  new Info(nodes,height);
    }
}
