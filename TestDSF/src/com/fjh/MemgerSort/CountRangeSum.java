package com.fjh.MemgerSort;
//https://leetcode-cn.com/problems/count-of-range-sum/submissions/
public class CountRangeSum {
    public static int countRangeSum(int []arr,int power,int upper){
        if(arr == null||arr.length == 0){
            return 0;
        }
        //首先求出前缀和
        int[]sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i-1]+arr[i];
        }
        return count(sum,0,sum.length-1,upper,power);
    }
    public static int count(int[]sum,int left,int right,int power,int upper){

        if(left == right){//只有一个元素,只要这个元素在范围内即可
            if(sum[left] >= upper&&sum[right] <= power){
                return 1;
            }else{
                return 0;
            }
        }
        int mind = left+((right-left)>>1);
        int leftCount = count(sum,left,mind,power,upper);
        int rightCount = count(sum,mind+1,right,power,upper);
        int mergeCount = merge(sum,left,mind,right,power,upper);
        return leftCount+rightCount+mergeCount;
    }
    public static int merge(int[]sum,int left,int mind,int right,int power,int upper){
        int ans = 0;
        int windowLeft = left;
        int windowRight = left;
        for(int i = mind+1;i <= right; i++){
            int max = sum[i]- upper;
            int min = sum[i]-power;
            while(windowLeft <= mind  && sum[windowLeft] < min){
                windowLeft++;
            }
            while (windowRight <= mind && sum[windowRight] <= max){
                windowRight++;
            }
            ans += (windowRight-windowLeft);
        }
        //进行归并
        int L=left;
        int R=mind+1;
        int k=0;
        int[]temp=new int[right-left+1];
        while (L<=mind&&R<=right){
            temp[k++]=sum[L]<=sum[R]?sum[L++]:sum[R++];
        }
        while (L<=mind){
            temp[k++]=sum[L++];
        }
        while (R<=right){
            temp[k++]=sum[R++];
        }
        for (int i=0;i<temp.length;i++){
            sum[i+left]=temp[i];
        }
        //返回答案
        return ans;
    }

    public static void main(String[] args) {
        int[]arr={0,0};
        System.out.println(countRangeSum(arr, 0, 0));
    }
}
