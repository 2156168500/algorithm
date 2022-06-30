package com.fjh.quicksort;
//https://leetcode-cn.com/problems/sort-colors/submissions/
public class SortColors {
    public static void swap(int[]arr,int a,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
    public void sortColors(int[] nums) {
        int min = -1;
        int max = nums.length-1;
        int i = 0;
        while(i <= max){
            if(nums[i] < 1){
                swap(nums,++min,i++);
            }else if(nums[i] > 1){
                swap(nums,max--,i);
            }else{
                i++;
            }
        }
    }
}
