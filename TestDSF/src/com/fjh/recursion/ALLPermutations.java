package com.fjh.recursion;

import java.util.ArrayList;
import java.util.List;

public class ALLPermutations {
    public static ArrayList<String> allPermutation(String s){
        ArrayList<String> ans = new ArrayList<>();
        if(s == null || s.length() == 0){
            return ans;
        }
        ArrayList<Character>str = new ArrayList<>();
        for (int i = 0 ;i< s.length() ;i++){
            str.add(s.charAt(i));
        }
        String path = "";
        precess(str,path,ans);
        return ans;
    }
    public static void precess(ArrayList<Character>str,String path,ArrayList<String> ans){
        if(str.isEmpty()){
            ans.add(path);
          return ;
        }
        int N = str.size();
        for (int i = 0; i < N ; i++){
            char cur = str.get(i);
            str.remove(i);
            precess(str,path+cur,ans);
            str.add(i,cur);
        }
    }
    public static ArrayList<String> allPermutation2(String s){
        ArrayList<String>ans = new ArrayList<>();
        if(s == null || s.length() == 0){
            return ans ;
        }
        char[]str = s.toCharArray();
        precess2(str,0,ans);
        return ans;
    }
    public static void precess2(char[]str,int index,ArrayList<String>ans){
        if(index == str.length){
            ans.add(String.valueOf(str));
            return ;
        }
        boolean[] check = new boolean[256];
        for (int i = index; i < str.length ; i++) {
            if(!check[str[i]]){
                check[str[i]] = true;
                swap(str, i, index);
                precess2(str, index + 1, ans);
                swap(str, i, index);
            }
        }
    }
    public static  void swap(char[]str,int i ,int j){
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }
    public static void main(String[] args) {
        String str = "acc";
     List<String> ans =allPermutation(str);
     List<String> ans2 =allPermutation2(str);
        for(String s: ans){
            System.out.print(s+" ");
        }
        System.out.println();
       for(String s: ans2){
           System.out.print(s+" ");
       }
    }
}
