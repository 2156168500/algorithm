package com.fjh.monotonousStack;

import java.util.Stack;

/**
 *
 * https://leetcode-cn.com/problems/sum-of-subarray-minimums/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china
 907. 子数组的最小值之和

 给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。

 由于答案可能很大，因此 返回答案模 10^9 + 7 。



 示例 1：

 输入：arr = [3,1,2,4]
 输出：17
 解释：
 子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
 最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。

 示例 2：

 输入：arr = [11,81,94,43,3]
 输出：444

 */
public class minSumSubArr {
    /**
     *当遇到类似于求子数组,子串的问题时,就要考虑,当i位置怎么样,然后结果会怎么样.
     * 这个题的了流程就可以表示为:
     * 以每一个位置的值为最小值,求的所有以这个位置为最小值的情况下,所有的子数组的和
     * 要得到每个位置作为最小值的情况,就要使用单调栈结构
     * 依据单调栈结构,就可以得到,每个位置,左边离得最近且比该位置小的位置L
     * 以及一右边离得最近且比给位置小的位置R
     * 那么得到的以该位置为最小的子数组就是,从L+1 开始到 R - 1这个区间上所有跨过该位置的数组
     * 这些数组的数量为:(i - l)*(r - i)
     *所得到的最小值的和为 arr[i] * (i - l)*(r - i)
     */
    public int sumSubarrayMins(int[] arr) {
        if(arr== null || arr.length == 0){
            return 0;
        }
        int[][]nearLessArr = nearLess(arr);
        long ans = 0;
        for (int i = 0; i < nearLessArr.length ; i++){
           long right = nearLessArr[i][0] - i;
            long left =i - nearLessArr[i][1];
            ans += (right  * left) * (long) arr[i];
            ans %= 1000000007;
        }
        return  (int) ans;
    }

    /**
     * 单调栈的执行流程
     * 1
     */
    public static int [][]nearLess(int[]arr){
        int [][] ret = new int[arr.length][2];
        int[]stack = new int[arr.length];
        int peek = -1;
        for (int i = 0; i < arr.length ; i++){
            while (peek != -1 && arr[stack[peek]] >= arr[i]){
                int cur = stack[peek--];
                int right = i;
                int left = peek == -1 ? -1 : stack[peek];
                ret[cur][0] = left;
                ret[cur][1] = right;
            }
            stack[++peek] = i;
        }
        while (peek != -1){
            int cur = stack[peek--];
            int right = arr.length;
            int left = peek == -1 ? -1 : stack[peek];
            ret[cur][0] = left;
            ret[cur][1] = right;    
        }
        return ret;
    }
}
