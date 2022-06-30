package com.fjh.tree;

/**
 * 是否是搜索二叉树
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 *
 * 有效 二叉搜索树定义如下：
 *
 *     节点的左子树只包含 小于 当前节点的数。
 *     节点的右子树只包含 大于 当前节点的数。
 *     所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/validate-binary-search-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class isBST {
    public boolean isValidBST(TreeNode root) {
        if(root == null){
            return  true;
        }
        return  process(root).isBst;

    }
    public static  class  Info{
        public boolean isBst;
        public int max;
        public int min;

        public Info(boolean isBst, int max, int min) {
            this.isBst = isBst;
            this.max = max;
            this.min = min;
        }
    }
    public Info process(TreeNode node){
        if(node == null){
            return null;
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int min = node.val;
        if (leftInfo != null){
            min = leftInfo.min;;
        }
        int max = node.val;
        if(rightInfo != null){
            max = rightInfo.max;
        }
        boolean isBst = true;
        if(leftInfo != null && !leftInfo.isBst){
            isBst = false;
        }
        if(rightInfo != null && !rightInfo.isBst){
            isBst = false;
        }
        if(leftInfo != null && leftInfo.max >= node.val){
            isBst = false;
        }
        if(rightInfo != null && rightInfo.min <= node.val){
            isBst = false;
        }
        return  new Info(isBst,max,min);
    }
}
