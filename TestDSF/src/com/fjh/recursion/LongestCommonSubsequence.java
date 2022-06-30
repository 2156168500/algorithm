package com.fjh.recursion;

import java.util.Locale;

public class LongestCommonSubsequence {
    public static int longestCommonSubsequence(String str1, String str2) {
        if(str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0){
            return  0;
        }
        char[]s1 = str1.toCharArray();
        char[]s2 = str2.toCharArray();
        return  process(s1,s2,s1.length - 1,s2.length - 1);
    }

    /**
     *str1和str2 在[0,i]和[0,j]位置的最长公共子序列
     *
     */
    public static int process(char[]str1,char[]str2,int i,int j){
        if(i == 0 && j == 0){
            return str1[i] == str2[j] ? 1 : 0 ;
        }
        if(i == 0){
            for (int k = j ; k >= 0 ; k--){
                if(str2[k] == str1[i]){
                    return  1;
                }
            }
            return  0;
        }else if(j == 0){
            for(int k =i ; k <= 0 ; k--){
                if(str1[k] == str2[j]){
                    return  1;
                }
            }
            return  0;
        }else{
            int p1 = process(str1,str2,i, j - 1);
            int p2 = process(str1,str2,i - 1,j);
            int p3 = str1[i] == str2[j] ?(1 + process(str1,str2,i - 1,j - 1) ): 0;
            return Math.max(p1,Math.max(p2,p3));
        }
    }

    public static int longestCommonSubsequence2(String str1, String str2) {
        if(str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0){
            return  0;
        }
        char[]s1 = str1.toCharArray();
        char[]s2 = str2.toCharArray();
        int N = s1.length;
        int M = s2.length;
        int[][]dp = new int[N][M];
        dp[0][0] = s1[0] == s2[0] ? 1 : 0;
        for(int j = 1 ; j < M ; j++){
            dp[0][j] = s1[0] == s2[j] ? 1 : dp[0][j - 1];
        }
        for(int i = 1; i< N ; i++){
            dp[i][0] = s1[i] == s2[0] ? 1 : dp[i - 1][0];
        }
        for (int i = 1; i < N ; i++){
            for(int j = 1 ; j < M ; j++){
                int p1 = dp[i ][ j - 1];
                int p2 = dp[i - 1][j];
                int p3 = s1[i] == s2[j] ? 1 + dp[i - 1][ j - 1] : 0;
                dp[i][j] = Math.max(p1,Math.max(p2,p3));
            }
        }
        return dp[N - 1][M - 1];
    }
    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abcde", "abe"));
    }
}
