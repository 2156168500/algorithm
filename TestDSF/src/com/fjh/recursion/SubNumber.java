package com.fjh.recursion;

public class SubNumber {
    public  static int ways(int N){
        if(N == 1 || N <= 0){
            return  1;
        }
        return  process(1,N);
    }
    public static int process(int pre,int rest){
        if(rest == 0){
            return 1;
        }
        if(pre == rest){
            return  1;
        }
        int ways = 0;
        for (int first = pre; first <= rest; first++) {
            ways += process(first, rest - first);
        }
        return  ways;
    }

    public static  int ways2(int N){
        if(N < 0){
            return  0;
        }
        if(N == 0){
            return  1;
        }
        if(N == 1){
            return  1;
        }
        int[][]dp = new int[N + 1][N + 1];
        for(int pre = 1;pre <= N ; pre ++){
            dp[pre][0] = 1;
        }
        for(int i = 1 ; i <= N ; i++){
            dp[i][i] = 1;
        }
       for (int pre = N - 1 ; pre >=0 ; pre--){
           for(int rest = pre + 1;rest <=N; rest++){
               int ways = 0;
               for (int first = pre; first <= rest; first++) {
                   ways += dp[first][rest- first];
               }
               dp[pre][rest] = ways;
           }
       }
       return  dp[1][N];
    }

    public static int ways3(int N){
        if(N  < 0){
            return 0;
        }
        if(N == 1 || N == 0){
            return 1;
        }
        int[][]dp = new int[N + 1][N + 1];
        for(int pre = 1;pre <= N ; pre ++){
            dp[pre][0] = 1;
        }
        for(int i = 1 ; i <= N ; i++){
            dp[i][i] = 1;
        }
        for (int pre = N - 1 ; pre >=0 ; pre--){
            for(int rest = pre + 1;rest <=N; rest++){
               dp[pre][rest] = dp[pre + 1][rest] + dp[pre][rest - pre];
            }
        }
        return  dp[1][N];
    }

















    public static int dp1(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                int ways = 0;
                for (int first = pre; first <= rest; first++) {
                    ways += dp[first][rest - first];
                }
                dp[pre][rest] = ways;
            }
        }
        return dp[1][n];
    }

    public static int dp2(int n) {
        if (n < 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int[][] dp = new int[n + 1][n + 1];
        for (int pre = 1; pre <= n; pre++) {
            dp[pre][0] = 1;
            dp[pre][pre] = 1;
        }
        for (int pre = n - 1; pre >= 1; pre--) {
            for (int rest = pre + 1; rest <= n; rest++) {
                dp[pre][rest] = dp[pre + 1][rest];
                dp[pre][rest] += dp[pre][rest - pre];
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        int test = 100;
        System.out.println(ways(test));
        System.out.println(dp1(test));
        System.out.println(dp2(test));
        System.out.println(ways2(test));
        System.out.println(ways3(test));
    }
}
