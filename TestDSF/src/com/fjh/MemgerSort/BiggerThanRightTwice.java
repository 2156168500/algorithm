package com.fjh.MemgerSort;

import java.util.Arrays;

public class BiggerThanRightTwice {
    public static int  biggerThanRightTwice(int[]array){
        if(array == null||array.length < 2){
            return 0;
        }
        return process(array,0, array.length-1);
    }
    public static int process(int[]arr,int left,int right){
        if(left>=right){
            return 0;
        }
        int mind=left+((right-left)>>1);
       return  process(arr, left,mind)+
          process(arr,mind+1,right)+merger(arr,left,mind,right);
    }
    public static int merger(int []arr,int left,int mind,int right){
        int ans = 0;
        int windowR = mind+1;
        for(int i = left;i <= mind;i++){
            while (windowR <= right && arr[i] > (arr[windowR]*2)){
                windowR++;
            }
            ans+=(windowR-mind-1);
        }
        int []help=new int[right-left+1];
        int k = 0;
        int R = mind+1;
        int L = left;
        while (L<=mind&&R<=right){
            help[k++] = arr[L]<=arr[R]?arr[L++]:arr[R++];
        }
        while (L<=mind){
            help[k++] = arr[L++];
        }
        while (R<=right){
            help[k++] = arr[R++];
        }
        for(int i=0;i< help.length;i++){
            arr[left+i] = help[i];
        }
        return ans;
    }
    // for test
    public static int comparator(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 50;
        int maxValue = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            if ( biggerThanRightTwice(arr1)!= comparator(arr2)) {
                System.out.println("Oops!");
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println("测试结束");

    }

}
