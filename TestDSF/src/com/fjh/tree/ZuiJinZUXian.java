package com.fjh.tree;

public class ZuiJinZUXian {
    public static class Info{
        public boolean findP;
        public boolean findQ;
        public TreeNode ans;
        public Info(boolean findP,boolean findQ,TreeNode ans){
            this.findP = findP;
            this.findQ = findQ;
            this.ans = ans;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return  process(root,p,q).ans;
    }
    public Info process(TreeNode node,TreeNode p,TreeNode q){
        if(node == null){
            return new Info(false,false,null);
        }
        Info leftInfo = process(node.left,p,q);
        Info rightInfo = process(node.right,p,q);
        boolean findP = leftInfo.findP || rightInfo.findP || node == p;
        boolean findQ = leftInfo.findQ || rightInfo.findQ || node == q;
        TreeNode ans =null;
        if(leftInfo.ans != null){
            ans = leftInfo.ans;
        }else if(rightInfo.ans != null){
            ans = rightInfo.ans;
        }else if(findP && findQ){
            ans = node;
        }
        return new Info(findP,findQ,ans);
    }

}
