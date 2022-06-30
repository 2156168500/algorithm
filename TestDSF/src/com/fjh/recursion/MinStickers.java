package com.fjh.recursion;

import java.util.HashMap;

/**
 * https://leetcode-cn.com/problems/stickers-to-spell-word/submissions/
 */
public class MinStickers {
    public int minStickers1(String[] stickers, String target) {
        if(stickers == null || target == null){
            return  0;
        }
        int ans = process(stickers,target);
        return  ans == Integer.MAX_VALUE ? -1 : ans;
    }
    public static int process(String[]strings,String target){
        if(target.length() == 0){
            return  0;
        }
        int min = Integer.MAX_VALUE;
        for (String s: strings) {
            String rest = minus(s,target);
            if(rest.length() !=target.length()){
             min = Math.min(min,process(strings,rest));
            }
        }
        min += min == Integer.MIN_VALUE ? 0 : 1;
        return min;
    }
    public static String minus(String str1,String str2){
        char []s1 = str1.toCharArray();
        char []s2 = str2.toCharArray();
        int[]counts = new int[27];
        for (int i = 0 ;i < s2.length ; i++){
            counts[s2[i] - 'a'] ++;
        }
        for (char ch:s1) {
            counts[ch - 'a']--;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 26; i++){
            if(counts[i] > 0){
                for (int j = counts[i]; j > 0;j--){
                   builder.append((char)(i + 'a') );
                }
            }
        }
        return builder.toString();
    }
    public int  minStickers2(String[] stickers, String target) {
        if(stickers == null || target == null){
            return  0;
        }
        int N = stickers.length;
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[]chars = stickers[i].toCharArray();
            for (char ch:
                 chars) {
                counts[i][ch - 'a']++;
            }
        }
       int ans = process2(counts,target);
        return  ans == Integer.MAX_VALUE ? -1 : ans;
    }
    public static  int process2(int[][]counts,String target){
       if(target.length() == 0){
           return  0;
       }
       char[]chars = target.toCharArray();
       int[]tCont = new int[27];
        for (char ch:
           chars  ) {
            tCont[ch - 'a']++;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < counts.length; i++){
            int []sticker  = counts[i];
            if(sticker[chars[0] - 'a'] > 0){
                StringBuilder builder = new StringBuilder();
                for (int j= 0;j < 26 ;j++){
                    if(tCont[j] > 0){
                        int num = tCont[j] -sticker[j];
                        for (int k = 0; k < num;k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String ret = builder.toString();
                min = Math.min(min,process2(counts,ret));
            }
        }
        return min +( min == Integer.MAX_VALUE ? 0 : 1);
    }
    public int  minStickers3(String[] stickers, String target) {
        if(stickers == null || target == null){
            return  0;
        }
        int N = stickers.length;
        int[][] counts = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[]chars = stickers[i].toCharArray();
            for (char ch:
                    chars) {
                counts[i][ch - 'a']++;
            }
        }
        HashMap<String,Integer>dp = new HashMap<>();
        int ans = process3(counts,target,dp);
        return  ans == Integer.MAX_VALUE ? -1 : ans;
    }
    public static  int process3(int[][]counts,String target,HashMap<String,Integer>dp){
        if(dp.containsKey(target)){
            return  dp.get(target);
        }
        if(target.length() == 0){
            return  0;
        }
        char[]chars = target.toCharArray();
        int[]tCont = new int[27];
        for (char ch:
                chars  ) {
            tCont[ch - 'a']++;
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < counts.length; i++){
            int []sticker  = counts[i];
            if(sticker[chars[0] - 'a'] > 0){
                StringBuilder builder = new StringBuilder();
                for (int j= 0;j < 26 ;j++){
                    if(tCont[j] > 0){
                        int num = tCont[j] -sticker[j];
                        for (int k = 0; k < num;k++) {
                            builder.append((char) (j + 'a'));
                        }
                    }
                }
                String ret = builder.toString();
                min = Math.min(min,process3(counts,ret,dp));
            }
        }
      int ans = min +( min == Integer.MAX_VALUE ? 0 : 1);
        dp.put(target,ans);
        return  ans;
    }
}
