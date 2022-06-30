package com.fjh.MemgerSort;

import java.util.Arrays;

public class MergeSort {
    public static void mergeSort1(int[]arr){
        if(arr==null||arr.length<1){
            return ;
        }
        process(arr,0,arr.length-1);
    }
    public static void process(int[]arr,int left,int right){
        if(left==right){
            return ;
        }
        int mind=left+((right-left)>>1);
        process(arr,left,mind);
        process(arr,mind+1,right);
        merge(arr,left,mind,right);
    }
    public static void merge(int[]arr,int left,int mind,int right){
        int L=left;
        int R=mind+1;
        int k=0;
        int[]temp=new int[right-left+1];
        while (L<=mind&&R<=right){
           temp[k++]=arr[L]<=arr[R]?arr[L++]:arr[R++];
        }
        while (L<=mind){
            temp[k++]=arr[L++];
        }
        while (R<=right){
            temp[k++]=arr[R++];
        }
        for (int i=0;i<temp.length;i++){
            arr[i+left]=temp[i];
        }
    }
    /**
     * 归并排序非递归方法
     *
     */
    public static void mergeSort2(int []arr){
        if(arr==null||arr.length<2){
            return ;
        }
        //准备步长
        int mergeSize=1;
        //数组的最后一个元素的位置
        int N=arr.length-1;
        while (mergeSize<=N){
            int L=0;//左组的第一个元素的位置
            while (L<=N){
                if (mergeSize > N - L) {
                    break;
                }
                int M=L+mergeSize-1; //左组的最后一个元素的位置
                if(M>=N){
                    break;
                }
                int R=Math.min(M+ mergeSize,N);
               // int R = M + Math.min(mergeSize, N - M - 1);
                merge(arr,L,M,R);
                L=R+1;
            }
            if(mergeSize>N/2){
                break;
            }
            mergeSize<<=1;
        }
    }

//    public static void main(String[] args) {
//        int[]a={4,3,2,1};
//        mergeSort2(a);
//        System.out.println(Arrays.toString(a));
//    }
}
