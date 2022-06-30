package com.fjh.tree;
/**
 * https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof/submissions/
 * 二叉树的序列化和反序列化
 */

import java.util.LinkedList;
import java.util.Queue;

public class Codec {

    // Encodes a tree to a single string.
    //先序变历序列化
    public String serialize(TreeNode root) {
        if(root == null){
            return null;
        }

        //String ans = preOderSerialize(root);
        String ans = leaverSerialize(root);
        return  ans.substring(0,ans.length()-1);
    }

    private String preOderSerialize(TreeNode root) {
        String str = new String();
        if(root == null){
            str+="null,";
        }else{
//            str+=(String.valueOf(root.val)+",");
              str+=(root.val+",");
              str+=preOderSerialize(root.left);
              str+=preOderSerialize(root.right);
        }
        return str;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.length()==0){
            return null;
        }
        String []str = data.split(",");
        Queue<String> ans = new LinkedList<>();
        for(int i = 0 ;i <str.length ;i++){
            ans.add(str[i]);
        }
        TreeNode root = leverDeserialize(ans);
       return  root;
    }

    private TreeNode preOderDeserialize(Queue<String> ans) {
        if(ans == null || ans.size() == 0){
            return null;
        }
        if(ans.peek().equals("null")){
            ans.poll();
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(ans.poll()));
        root.left = preOderDeserialize(ans);
        root.right= preOderDeserialize(ans);
        return  root;
    }
    private  String leaverSerialize(TreeNode root){
        String ans = new String();
        ans+=(root.val+",");
        Queue<TreeNode>queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            root = queue.poll();
            if(root.left == null){
                ans+="null,";
            }else {
                queue.add(root.left);
                ans+=(root.left.val+",");
            }
            if(root.right == null){
                ans+="null,";
            }else{
                queue.add(root.right);
                ans+=(root.right.val+",");
            }
        }
        return ans;
    }
    private  TreeNode leverDeserialize(Queue<String> ans){
        Queue<TreeNode> queue = new LinkedList<>();
        if(ans.peek().equals("null")){
            return null;
        }
        TreeNode root = new TreeNode(Integer.valueOf(ans.poll()));
        TreeNode ret = root;
        queue.add(root);
        while (!queue.isEmpty()&&!ans.isEmpty()){
            root = queue.poll();
            root.left = createNode(ans.poll());
            root.right = createNode(ans.poll());
            if(root.left != null){
                queue.add(root.left);
            }
            if(root.right != null){
                queue.add(root.right);
            }
        }
        return ret;
    }
    private TreeNode createNode(String s){
        if(s.equals("null")){
            return null;
        }else {
            return new TreeNode(Integer.valueOf(s));
        }
    }
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;
        Codec c = new Codec();
        String s = c.serialize(node1);
//        System.out.println(s);
//        System.out.println(Arrays.toString(s.split(",")));
        TreeNode root = c.deserialize(s);

      //  System.out.println(c.serialize(node1));

    }
}
