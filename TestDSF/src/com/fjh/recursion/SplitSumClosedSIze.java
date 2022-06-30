package com.fjh.recursion;

/**
 * 给定一个正数数组arr，请把arr中所有的数分成两个集合
 * 如果arr长度为偶数，两个集合包含数的个数要一样多
 * 如果arr长度为奇数，两个集合包含数的个数必须只差一个
 * 请尽量让两个集合的累加和接近
 * 返回：
 * 最接近的情况下，较小集合的累加和
 */
public class SplitSumClosedSIze {


    public static int right(int[] arr) {
        if(arr == null || arr.length < 2){
            return  0;
        }
        int sum = 0;
        for(int n :arr){
            sum += n;
        }
        int n = arr.length ;
        int pricks = 0;
        int ans1 = Integer.MIN_VALUE;
        int ans2 = Integer.MIN_VALUE;
        if(n % 2 == 0){//有偶数个数
             pricks = n / 2;
             ans1 = process(arr,0,pricks,sum / 2);
        }else{//有奇数个数,这个较小的集合的个数可能是 n/2 也可能是 n/2 + 1,所以就就去prices 为(n + 1 )/2;
            pricks = (n +  1) / 2 ;
            int p1 = process(arr,0,pricks,sum/2);
            int p2 = process(arr,0,pricks - 1,sum/2);
            ans2 = Math.max(p1,p2);
        }
        return Math.max(ans1,ans2);
    }
    //arr[index ... arr.length - 1]的区间上,任意组合,使得这个组合的个数==picks 组成的数的和不超过rest
    //返回组成数的和
    public static int process(int[] arr, int index, int picks, int rest) {
        if(index == arr.length){//没有数了
            return  picks == 0 ? 0 :-1 ;
        }
        int p1 = process(arr,index + 1,picks, rest);
        int p2 = Integer.MIN_VALUE;
        if(arr[index] <= rest){
            p2 = process(arr,index + 1,picks - 1, rest - arr[index]);
        }
        if(p2 != -1){
            p2 += arr[index];
        }
        return Math.max(p1,p2);
    }
    public  static int dp(int[]arr){
        if(arr == null || arr.length < 2){
            return  0;
        }
        int sum = 0;
        for (int n: arr){
            sum += n ;
        }
        int N = arr.length;
        int pricks = (N +1) /2;
        sum /= 2;
       int  dp[][][] = new int[N+ 1 ][pricks + 1][sum + 1];
       for(int i = 0; i <= N ;i++){
           for(int j = 0; j <= pricks ; j ++){
               for (int rest = 0 ; rest <= sum ; rest++){
                   dp[i][j][rest] =  -1 ;
               }
           }
       }
       for(int rest = 0 ; rest <= sum ; rest++){
           dp[N][0][rest] = 0;
       }
       for(int index = N- 1; index >= 0; index -- ){
           for(int p = 0; p <= pricks ; p++){
               for (int rest = 0 ; rest <= sum ; rest++){
                   int p1 = dp[index + 1][p][rest];
                   int p2 = -1;
                   if(p - 1 >=0 && rest>=arr[index])
                    p2 = dp[index + 1][p -1][rest - arr[index]];
                   if(p2 != -1){
                       p2 += arr[index];
                   }
                   dp[index][p][rest] = Math.max(p1,p2);
               }
           }
       }
       if(N%2 == 0){
           return  dp[0][ pricks][sum];
       }else{
           int p1 = dp[0][pricks - 1][sum];
           int p2 = dp[0][pricks][sum];
           return  Math.max(p1,p2);
       }
    }
//    public static int dp(int[] arr) {
//        if (arr == null || arr.length < 2) {
//            return 0;
//        }
//        int sum = 0;
//        for (int num : arr) {
//            sum += num;
//        }
//        sum /= 2;
//        int N = arr.length;
//        int M = (N + 1) / 2;
//        int[][][] dp = new int[N + 1][M + 1][sum + 1];
//        for (int i = 0; i <= N; i++) {
//            for (int j = 0; j <= M; j++) {
//                for (int k = 0; k <= sum; k++) {
//                    dp[i][j][k] = -1;
//                }
//            }
//        }
//        for (int rest = 0; rest <= sum; rest++) {
//            dp[N][0][rest] = 0;
//        }
//        for (int i = N - 1; i >= 0; i--) {
//            for (int picks = 0; picks <= M; picks++) {
//                for (int rest = 0; rest <= sum; rest++) {
//                    int p1 = dp[i + 1][picks][rest];
//                    // 就是要使用arr[i]这个数
//                    int p2 = -1;
//                    int next = -1;
//                    if (picks - 1 >= 0 && arr[i] <= rest) {
//                        next = dp[i + 1][picks - 1][rest - arr[i]];
//                    }
//                    if (next != -1) {
//                        p2 = arr[i] + next;
//                    }
//                    dp[i][picks][rest] = Math.max(p1, p2);
//                }
//            }
//        }
//        if ((arr.length & 1) == 0) {
//            return dp[0][arr.length / 2][sum];
//        } else {
//            return Math.max(dp[0][arr.length / 2][sum], dp[0][(arr.length / 2) + 1][sum]);
//        }
//    }
    public static int dp2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        sum >>= 1;
        int N = arr.length;
        int M = (arr.length + 1) >> 1;
        int[][][] dp = new int[N][M + 1][sum + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= M; j++) {
                for (int k = 0; k <= sum; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int k = 0; k <= sum; k++) {
                dp[i][0][k] = 0;
            }
        }
        for (int k = 0; k <= sum; k++) {
            dp[0][1][k] = arr[0] <= k ? arr[0] : Integer.MIN_VALUE;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= Math.min(i + 1, M); j++) {
                for (int k = 0; k <= sum; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (k - arr[i] >= 0) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - 1][k - arr[i]] + arr[i]);
                    }
                }
            }
        }
        return Math.max(dp[N - 1][M][sum], dp[N - 1][N - M][sum]);
    }

    // for test
    public static int[] randomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * value);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int maxLen = 10;
        int maxValue = 50;
        int testTime = 10000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int len = (int) (Math.random() * maxLen);
            int[] arr = randomArray(len, maxValue);
           // int[]arr={10,39,4,26};
            int ans1 = right(arr);
            int ans2 = dp(arr);
            int ans3 = dp2(arr);
            if (ans1 != ans2 || ans1 != ans3) {
                printArray(arr);
                System.out.println(ans1);
                System.out.println(ans2);
                System.out.println(ans3);
                System.out.println("Oops!");
                break;
            }
        }
        System.out.println("测试结束");
    }
}
