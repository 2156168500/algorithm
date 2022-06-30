package com.fjh.bfprt;

import java.util.Arrays;

/**
 * 求数数组中第看k小的数,数组不为空,k >0 && k < array.length
 */
public class FindMinKth {
    /**
     * 方法一:改写快排
     * 1.在数组中随机选取一个数n
     * 2.将数组调整为等于n的放中间,大于n的放右边,小于n的放左边
     * 3.如果k在等于区域中直接返回,如果在大于区域中,则在大于区域中重复上述过程,
     */

    public static  int findMinKth(int[]array,int k){
        return process(array,0,array.length - 1,k -1);
    }
    //在数组的[L, R]区间上,找到排序后小标为index的数,index在[L,R]上
    public static int process(int[]array,int L ,int R ,int index){
        if(L == R){//如果区间上只有一个数,那么必然就是要找的数,因为
            return array[L];
        }
        //随机选取一个数pivot
        int pivot =L + (int)(Math.random() * (R - L + 1));
        //得到等于区域的作边界和右边界
        int [] range = partition(array,L , R ,pivot);
        if(index >= range[0] && index <= range[1]){//如果在等于区域内直接返回
            return array[index];
        }else if(index > range[1]){
            return  process(array,range[1] + 1 ,R,index);
        }else {
            return process(array,L ,range[0] - 1,index);
        }
    }
    public static int[] partition(int []array,int L,int R,int pivot){
        System.out.println(Arrays.toString(array));
        int [] range = new int[2]  ;
        int less = L - 1;
        int more = R + 1;
        int cur = L ;
        while (cur < more){
            if(array[cur] < array[pivot]){
                swap(array,cur++,++less);
            }else if(array[cur] > array[pivot]){
                swap(array,cur,--more);
            }else {
                cur++;
            }
        }
        range[0] = less + 1;
        range[1] = more - 1;
        return  range;
    }
    public static void swap(int []arr,int i ,int j){
        int k = arr[i];
        arr[i] = arr[j];
        arr[j] = k;
    }
    //for test
    public static  int findKth(int[]arr,int k){
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        return arr[k - 1];
    }

    public static void main(String[] args) {
        int[]arr = {1,8,0,9,5,4,4,0,7,3,5};
        int kth = findMinKth(arr,4);
        int Kth2 = findKth(arr,4);
        System.out.println(kth);
        System.out.println("right" + Kth2);
    }
}
