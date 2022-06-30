package com.fjh.recursion;

public class LongestPalindromeSubseq {
    public int longestPalindromeSubseq(String s) {
        if(s == null || s.length() == 0){
            return  0;
        }
        char[]arr = s.toCharArray();

      return   process(arr,0 ,arr.length);
    }
    public static int process(char[]arr,int l , int r){
        if(l == r){
            return 1;
        }
        if(l > r){
            return  0;
        }
        int p1 = process(arr,l + 1,r - 1);
        int p2 = process(arr,l + 1,r);
        int p3 = process(arr, l , r -1);
        int p4 = arr[l] == arr[r] ? 2 + process(arr,l +1,r -1) : 0;
        return Math.max(Math.max(p1,p2),Math.max(p3,p4));
    }
    public int longestPalindromeSubseq2(String s) {
        if(s == null || s.length() == 0){
            return  0;
        }
        char[]arr = s.toCharArray();
        int N = arr.length;
        int [][] dp  = new int[N][N];
        for (int i = 0; i < N; i++) {
            dp[i][i] = 1;
        }
        for (int l = N - 2; l >=0 ; l--){
            for(int r = l + 1 ; r < N ; r++){
                int p1 = dp[l][r - 1];
                int p2 = dp[l + 1][r];
                int p3 =dp [l][r -1];
                int p4 = arr[l] == arr[r] ? 2 +dp[l +1][r -1] : 0;
                dp[l][r] =Math.max(Math.max(p1,p2),Math.max(p3,p4));
            }
        }
        return   dp[0][N - 1];
    }
}
