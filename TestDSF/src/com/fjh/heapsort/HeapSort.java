package com.fjh.heapsort;

import java.util.Arrays;

public class HeapSort {
    public static  void heapSort(int[]arr){
        if(arr == null || arr.length < 2){
            return ;
        }
        for(int i = arr.length - 1;i >= 0;i--){
            heapIfy(arr,i,arr.length);
        }
        //O(N*longN)
//        for (int  i = 0;i < arr.length;i++){
//            heapInsert(arr,i);
//        }
        int heapSize = arr.length;
        swap(arr,0,--heapSize);
        while (heapSize > 0){
            heapIfy(arr,0,heapSize);
            swap(arr,0,--heapSize);
        }
    }
    //向上调整
    public static void heapInsert(int[]arr,int index){
        while (arr[index] > arr[(index - 1)/2] ){
            swap(arr,index,(index - 1) / 2);
            index = (index - 1) / 2;
        }
    }
//向下调整
    public static void heapIfy(int[]arr,int index,int heapSize){
        int left = index * 2 + 1;
        while (left < heapSize){
            int lage = ((left + 1 < heapSize) && (arr[left+1] > arr[left]) ) ? left+1 : left;
            lage = arr[index] > arr[lage] ? index : lage;
            swap(arr,index,lage);
            if(index == lage){
                break;
            }
            index = lage;
            left = index * 2 + 1;
        }
    }
   public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    // 生成随机数组（用于测试）
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // 拷贝数组（用于测试）
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

    // 对比两个数组（用于测试）
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

    // 打印数组（用于测试）
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


    //    // 跑大样本随机测试（对数器）
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            heapSort(arr1);
            Arrays.sort(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println("test end");
        System.out.println("测试" + testTime + "组是否全部通过：" + (succeed ? "是" : "否"));
    }
}
