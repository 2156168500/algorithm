package com.fjh.windows;

import java.util.Arrays;
import java.util.Stack;

/**
 * 给定一个只包含正数的数组arr，arr中任何一个子数组sub，
 * 一定都可以算出(sub累加和 )* (sub中的最小值)是什么，
 * 那么所有子数组中，这个值最大是多少？
 */
public class AllTimesMinToMax {
    public static int maxOFSubArr(int[]arr){
        //1.得到前缀和数组
        int [] sum = new int[arr.length];
        sum[0] = arr[0];
        for(int i = 1 ;  i < arr.length ; i++){
            sum[i]  = sum[i - 1 ] + arr[i];
        }
        //2.得到数组每个位置左边比它小的位置,和右边比它小的位置
        int [][]nearMin = new int[arr.length][2];
        Stack<Integer>stack = new Stack<>();
        for (int i = 0 ; i < arr.length ; i++){
            while (!stack.isEmpty() &&arr[stack.peek()] >= arr[i]){
                int polI = stack.pop();
                int rightIndex = i;
                int leftIndex = stack.isEmpty() ? -1 : stack.peek();
                nearMin[polI][0] = leftIndex;
                nearMin[polI][1] = rightIndex;
            }
            stack.add(i);
        }
        while (!stack.isEmpty()){
            int polI = stack.pop();
            int rightIndex = -1 ;
            int leftIndex = stack.isEmpty() ? -1 : stack.peek();
            nearMin[polI][0] = leftIndex;
            nearMin[polI][1] = rightIndex;
        }
        //3.求出一每个位置为最小值的结果,因为以每个位置作为最小值,所以这个最小值是确定的,要想得到最大的结果,
        //就要是的数组的累加和最大,因为,数组中的数都是正数,所以,以一个位置的数值为最小值,数组的长度越大.
        //累加和就越大.那么子数组的区间就是(右边比该位置小的,左边比该位置小的)
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length ; i ++){
            int left = nearMin[i][0] == -1 ? 0 : nearMin[i][0] + 1;
            int right = nearMin[i][1] == - 1 ? arr.length -1  : nearMin[i][1] - 1;
            int ans = (left == 0  ? sum[right] : (sum[right] -sum[left - 1] )) * arr[i];
            max = Math.max(max,ans);
        }
        return max;
    }




//for test

    public static int max1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }
    public static int[] gerenareRondomArray() {
        int[] arr = new int[(int) (Math.random() * 20) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 2000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerenareRondomArray();
            int ans1 = max1(arr);
            int ans2 = maxOFSubArr(arr);
            if (ans1 != ans2) {
                System.out.println(Arrays.toString(arr));
                System.out.println("ans1 = " + ans1);
                System.out.println("ans2 = " + ans2);
                System.out.println("fail");
                break;
            }
        }
        System.out.println("test finish");
    }
}
