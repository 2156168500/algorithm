package com.fjh.tree;

public class ISBalancesTress {
    public static  class InFo{
        public boolean isBalance;
        public int height;
        public InFo(boolean b ,int h){
            this.isBalance = b;
            this.height = h;
        }

    }
    public boolean isBalanced(TreeNode root) {
        return process(root).isBalance;
    }
    public InFo process(TreeNode node){
        if(node == null){
            return new InFo(true,0);
        }
        InFo leftInfo = process(node.left);
        InFo rightInfo = process(node.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isBalance = true;
        if(!leftInfo.isBalance){
            isBalance = false;
        }
        if(!rightInfo.isBalance){
            isBalance = false;
        }
        if(Math.abs(leftInfo.height- rightInfo.height) > 1){
            isBalance = false;
        }
        return new InFo(isBalance,height);
    }
}
