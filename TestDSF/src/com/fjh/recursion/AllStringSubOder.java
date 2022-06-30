package com.fjh.recursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AllStringSubOder {
    public static ArrayList<String>getAllSub(String s){
        ArrayList<String> ans = new ArrayList<>();
        if(s == null || s.length() == 0){
            return ans;
        }
        char[]str = s.toCharArray();
        String path = "";
        precess(0,str,path,ans);
        return  ans;
    }
    public static void precess(int index,char[]str,String path,ArrayList<String>ans){
        if(index == str.length){
            ans.add(path);
            return;
        }
        precess(index+1,str,path,ans);
        precess(index+1,str,path+str[index],ans);
    }
    public static Set<String> getAllSub2(String s){
        Set<String>ans = new HashSet<>();
        if(s == null || s.length() == 0){
            return ans;
        }
        char[]str = s.toCharArray();
        String path = "";
        precess2(0,str,path,ans);
        return  ans;
    }
    public static void precess2(int index,char[]str,String path,Set<String>ans){
        if(index == str.length){
          ans.add(path);
            return;
        }
        precess2(index+1,str,path,ans);
        precess2(index+1,str,path+str[index],ans);
    }

    public static void main(String[] args) {
        String s = "acc";
        ArrayList<String>strings = getAllSub(s);
        for (String str:
             strings) {
            System.out.print(str+" ");
        }
        System.out.println();
        System.out.println("______________________________");
        String s2 = "acc";
        Set<String> strings1 = getAllSub2(s);
        for (String str:
                strings1) {
            System.out.print(str+" ");
        }
    }


}
