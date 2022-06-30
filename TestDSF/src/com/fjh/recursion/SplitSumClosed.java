package com.fjh.recursion;

/**
 * 给定一个正数数组arr，
 * 请把arr中所有的数分成两个集合，尽量让两个集合的累加和接近
 * 返回：
 * 最接近的情况下，较小集合的累加和
 */
public class SplitSumClosed {
    public static int right(int[] arr) {
        if(arr == null || arr.length < 2){
            return  0;
        }
        int sum = 0;
        for(int s: arr){
            sum += s;
        }
        return process(arr,0 ,sum/2);
    }

    /**
     * 在arr数组中 index 位置开始 选择凑出小于等于rest较小的和
     */
    public static int process (int[]arr,int index,int rest){
        if(index == arr.length){
            return 0;
        }
        if(rest == 0){
            return 0;
        }
        int p1 = process(arr,index + 1 ,rest);
        int p2 = Integer.MIN_VALUE;
        if(arr[index] <= rest){
            p2 =  arr[index] + process(arr,index + 1, rest - arr[index]);
        }
        return  Math.max(p1,p2);
    }

    public static int dp1(int []arr){
        if(arr == null || arr.length < 2 ){
            return  0;
        }
        int sum = 0;
        for (int n: arr){
            sum += n;
        }
        sum/=2;
        int N = arr.length ;
        int[][] dp= new int[ N + 1][sum + 1];
        for(int index = N - 1 ; index >= 0 ; index --){
            for(int rest = 1 ; rest <= sum ; rest ++){
                int p1 = dp[index +  1][rest];
                int p2 = Integer.MIN_VALUE;
                if(arr[index] <= rest){
                    p2 =  arr[index] + dp[index + 1][ rest -arr[index]];
                }
               dp[index][rest] = Math.max(p1,p2);
            }
        }
        return dp[0][sum];
    }






















    public static int dp(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        sum /= 2;
        int N = arr.length;
        int[][] dp = new int[N + 1][sum + 1];
        for (int i = N - 1; i >= 0; i--) {
            for (int rest = 0; rest <= sum; rest++) {
                // 可能性1，不使用arr[i]
                int p1 = dp[i + 1][rest];
                // 可能性2，要使用arr[i]
                int p2 = 0;
                if (arr[i] <= rest) {
                    p2 = arr[i] + dp[i + 1][rest - arr[i]];
                }
                dp[i][rest] = Math.max(p1, p2);
            }
        }
        return dp[0][sum];
    }

    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
            int ans1 = right(arr);
            int ans2 = dp1(arr);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
