package com.fjh.quicksort;

import java.util.Arrays;
import java.util.Stack;

public class QuickSort {
    public static void quickSort1(int[]arr){
        if(arr == null || arr.length < 2){
            return ;
        }
        progress(arr,0, arr.length-1);
    }
    public static void swap(int[]arr,int a,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    public static void progress(int []arr,int left,int right){
        if(left >= right){
            return ;
        }
        swap(arr,left+ (int)((Math.random())*(right - left+1)),right);
        int []loads = netherlandsFlag(arr,left,right);
        progress(arr,left,loads[0]-1);
        progress(arr,loads[1]+1, right);
    }
    public static int[]netherlandsFlag(int[]arr,int left,int right){
        int less = left - 1;
        int more = right;
        int i = left;
        while (i < more){
            if(arr[i] < arr[right]){
                swap(arr,++less,i++);
            }else if(arr[i] > arr[right]){
                swap(arr,i,--more);
            }else{
                i++;
            }
        }
        swap(arr,right,more);
        return new int[]{less+1,more};
    }
    public static class InFo{
        public int left;
        public int right;
        public InFo(int left,int right){
            this.left = left;
            this.right = right;
        }
    }
    public static void quickSort2(int[]arr){
        if(arr == null || arr.length < 2){
            return ;
        }
        swap(arr,(int)(Math.random()*((arr.length))),arr.length - 1);
        int[]loads=netherlandsFlag(arr,0,arr.length - 1);
        Stack<InFo>stack=new Stack<>();
        stack.push(new InFo(0,loads[0] - 1 ));
        stack.push(new InFo(loads[1] + 1,arr.length - 1));
        while (! stack.isEmpty()){
            InFo inFo=stack.pop();
            if(inFo.left <  inFo.right){
                swap(arr, inFo.left +(int)((Math.random())*(inFo.right-inFo.left+1)), inFo.right );
                loads=netherlandsFlag(arr, inFo.left, inFo.right);
                stack.push(new InFo(inFo.left, (loads[0]-1)));
                stack.push(new InFo(loads[1]+1, inFo.right));
            }

        }
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
            quickSort2(arr1);
           quickSort1(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println("test end");
        System.out.println("测试" + testTime + "组是否全部通过：" + (succeed ? "是" : "否"));
    }

}
