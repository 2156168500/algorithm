package com.fjh.recursion;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 即便是值相同的货币也认为每一张都是不同的，
 * 返回组成aim的方法数
 * 例如：arr = {1,1,1}，aim = 2
 * 第0个和第1个能组成2，第1个和第2个能组成2，第0个和第2个能组成2
 * 一共就3种方法，所以返回3
 */
public class aimManyMax {
    public static int coinWays(int[] arr, int aim) {
       return process(arr, 0, aim);
    }

   public static int process(int[]arr,int index,int rest){
        if(index == arr.length){
           return  rest == 0 ? 1 : 0;
        }
        if(rest == 0){
            return 1;
        }
        int p1 = process(arr,index + 1 ,rest - arr[index]);
        int p2 = process(arr,index + 1,rest);
        return  p1 + p2;
   }
    public static int dp(int[] arr, int aim) {
        int n = arr.length;
        int dp[][] = new int[n+1][aim + 1];
        dp[n][0] = 1;
        for(int i = 0; i <= n ; i++){
            dp[i][0] = 1;
        }
        for(int index = n - 1 ; index >= 0 ; index --){
            for(int ret = 1 ; ret <= aim ; ret++){
              dp[index][ret] = dp[index + 1][ret] + (ret - arr[index] >=0 ?dp[index + 1][ret - arr[index]]:0);

            }
        }
        return dp[0][aim];
    }

    // 为了测试
    public static int[] randomArray(int maxLen, int maxValue) {
        int N = (int) (Math.random() * maxLen);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * maxValue) + 1;
        }
        return arr;
    }

    // 为了测试
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 为了测试
    public static void main(String[] args) {
        int maxLen = 20;
        int maxValue = 30;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int aim = (int) (Math.random() * maxValue);
            int ans1 = coinWays(arr, aim);
            int ans2 = dp(arr, aim);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(aim);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试通过");
        System.out.println("测试结束");
    }

}
