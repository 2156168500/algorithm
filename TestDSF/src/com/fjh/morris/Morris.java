package com.fjh.morris;

/**
 * morris遍历
 * 当前节点是cur节点
 * 1.如果cur没有左树就向右遍历
 * 2.如果cur有左树
 *   a:cur的左树的最右边界等于空,则左树的最右边界赋值为cur
 *   b:cur的左树的最右边界等于当前节点,则当前节点左树的最右节点恢复为空
 */
class TreeNode{
    public int vale;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(){

    }
    public TreeNode (TreeNode left,TreeNode right ,int vale){
        this.vale = vale;
        this.right = right;
        this.left = left ;
    }

}

public class Morris {
    public void morris(TreeNode head){
        if(head == null){
            return ;
        }
        TreeNode cur = head;//当前遍历到的节点
        TreeNode moreRight = null;//当前节点的左树的右边界的前一个
        while (cur != null){
            moreRight = cur.left;
            if(moreRight != null){//有左树
                while (moreRight.right != null && moreRight.right != cur){//找当前节点的左树的右边界
                    moreRight = moreRight.right;
                }
                if(moreRight.right == null){//第一次遍历到cur
                    //将当前节点的右边界设置为当前节点
                    moreRight.right = cur;
                    //当前节点先左跳
                    cur = cur.left;
                    //继续执行循环
                    continue;
                }else{//当前节点的左树的右边界是当前节点本身
                    //恢复当前节点的右边界为空
                    moreRight.right = null;
                }
            }
            //没有左树的情况
            cur = cur.right;
        }
    }
}
