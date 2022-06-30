package com.fjh.windows;

import java.util.LinkedList;

public class RightOfSubArr {
    //暴力解
    public static int  right(int []arr,int sum){
        int count = 0;
        for(int L = 0; L  < arr.length  ;L++){
            for(int R = L ; R < arr.length ; R++){
                int max = arr[L];
                int min = arr[R];
                for(int i = L ; i <= R ; i++){
                    max = Math.max(max,arr[i]);
                    min = Math.min(min,arr[i]);
                }
                if(max - min <= sum){
                    count++;
                }
            }

        }
        return count;
    }

    //滑动窗口解法
    public static int rightOfSubArr(int []arr,int sum){
        int N = arr.length;
        LinkedList<Integer> maxWindow = new LinkedList<>();
        LinkedList<Integer> minWindow = new LinkedList<>();
        int R = 0;
        int count = 0;
        for (int  L = 0 ; L < N ; L++){
            while (R <  N){
                while (!maxWindow.isEmpty()&&arr[maxWindow.peekLast()] <= arr[R]){
                    maxWindow.pollLast();
                }
                maxWindow.addLast(R);
                while (!minWindow.isEmpty() && arr[minWindow.peekLast()] >= arr[R]){
                    minWindow.pollLast();
                }
                minWindow.addLast(R);
                if(arr[maxWindow.peekFirst()]-arr[minWindow.peekFirst()] > sum){
                    break;
                }else {
                    R++;
                }
            }
            count += R - L;
            if(L == minWindow.peekFirst()){
                minWindow.pollFirst();
            }
            if(L == maxWindow.peekFirst()){
                maxWindow.pollFirst();
            }
        }
        return  count;
    }
    // for test
    public static int[] generateRandomArray(int maxLen, int maxValue) {
        int len = (int) (Math.random() * (maxLen + 1));
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxLen, maxValue);
            int sum = (int) (Math.random() * (maxValue + 1));
            int ans1 = right(arr, sum);
            int ans2 = rightOfSubArr(arr, sum);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                printArray(arr);
                System.out.println(sum);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");

    }
}
