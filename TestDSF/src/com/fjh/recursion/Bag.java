package com.fjh.recursion;
public class Bag {
    /**
     * 背包问题 从左到右的尝试
     * @param w  物品的重量数组
     * @param v  物品的价值数组
     * @param bag 背包的最大容量
     * @return 能获取的最大的价值
     */
    public static int bag(int []w,int []v,int bag){
        if(w == null || v == null || v.length != w.length || v.length == 0){
            return  0;
        }
        return process(w,v,0,bag);
    }

    /**\
     *
     * @param w 重量数组
     * @param v 价值数组
     * @param index 当前是那个下标的物品
     * @param rest 背包的剩余空间
     * @return 从index位置起获得的最大价值
     */
    public static int process(int[]w,int []v ,int index,int rest){
        if(rest < 0){
            return 0;
        }
        if(index ==w.length){
            return 0;
        }
        int p1 = process(w,v,index+1,rest);
        int p2 = 0;
        if(rest >= w[index]){
          p2 = v[index] + process(w,v,index + 1,rest - w[index]);
        }
        return Math.max(p1,p2);
    }
    public static int bag2(int[]w,int[]v,int dag){
        if(w == null || v == null || v.length != w.length || v.length == 0){
            return  0;
        }
        int N = w.length;
        int M = dag ;
        int [][]dp = new int[N + 1][M + 1];
        for(int index = N - 1; index >= 0 ; index--){
            for (int rest = 0 ; rest <= M; rest++){
                int p1 = dp[index + 1][rest];
                int p2 = 0;
                if(rest >= w[index]){
                    p2 = v[index] + dp[index + 1][rest - w[index]];
                }
                dp[index][rest] = Math.max(p1,p2);
            }
        }
        return dp[0][dag];
    }














    //test
    // 所有的货，重量和价值，都在w和v数组里
    // 为了方便，其中没有负数
    // bag背包容量，不能超过这个载重
    // 返回：不超重的情况下，能够得到的最大价值
    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        // 尝试函数！
        return process1(w, v, 0, bag);
    }

    // index 0~N
    // rest 负~bag
    public static int process1(int[] w, int[] v, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }
        int p1 = process1(w, v, index + 1, rest);
        int p2 = 0;
        int next = process1(w, v, index + 1, rest - w[index]);
        if (next != -1) {
            p2 = v[index] + next;
        }
        return Math.max(p1, p2);
    }
    public static void main(String[] args) {
        int[] weights = { 3, 2, 4, 7, 9, 1, 7 };
        int[] values = { 5, 6, 3, 19, 12, 4, 2 };
        int bag = 15;
        System.out.println(bag(weights, values, bag));
        System.out.println(maxValue(weights, values, bag));
        System.out.println(bag2(weights, values, bag));
    }
}
