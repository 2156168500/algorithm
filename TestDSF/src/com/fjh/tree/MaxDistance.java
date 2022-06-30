package com.fjh.tree;

/**
 * 二叉树节点间的最大距离
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 *
 *
 * 示例 :
 * 给定二叉树
 *
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 *
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/diameter-of-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxDistance {
    public int diameterOfBinaryTree(TreeNode root) {
        return  process(root).maxDistance;
    }
    public static class Info {
        public int maxDistance;
        public int height;
        public Info(int m,int h){
            this.maxDistance = m;
            this.height = h;
        }
    }
    public Info process(TreeNode node){
        if(node == null){
            return new Info(0,0);
        }
        Info leftInfo = process(node.left);
        Info rightInfo = process(node.right);
        int height = Math.max(rightInfo.height, leftInfo.height) + 1;
        int p = leftInfo.height + rightInfo.height + 1;
        int p1= leftInfo.maxDistance;
        int p2= rightInfo.maxDistance;
        int maxDistance = Math.max( Math.max(p1, p2),p);
        return new Info(maxDistance,height);
    }
}
