package com.fjh.bfprt;

import java.util.Arrays;

public class Bfprt {
    /**
     * bfprt算法的流程
     * 1.对要求解的数组五个数为一组进行分组
     * 2.对分好的组进行排序
     * 3.选出每一组中的中间值成立新数组
     * 4.以新数组中的中间值作为pivot进行partition
     */
    public  static int findMinKth(int []arr,int k){
        return bfprt(arr,0 ,arr.length - 1, k - 1 );
    }
    //在L->R上找排序后index位置的数
    public static int bfprt(int[]array,int L ,int R ,int index){
//        System.out.println("L = "+L + " R = " + R );
        if(L == R){
            return array[index];
        }
        //得到pivot
        int pivot = medianOfMedians(array,L ,R );
        //根据pivot进行partition过程
        int [] range = partition(array,L , R ,pivot);
        if(index >= range[0] && index <= range[1]){//如果在等于区域内直接返回
            return array[index];
        }else if(index > range[1]){
            return  bfprt(array,range[1] + 1 ,R,index);
        }else {
            return bfprt(array,L ,range[0] - 1,index);
        }
    }
    public static int[] partition(int []array,int L,int R,int pivot){
        int [] range = new int[2]  ;
        int less = L - 1;
        int more = R + 1;
        int cur = L ;
        while (cur < more){
            if(array[cur] < pivot){
                swap(array,cur++,++less);
            }else if(array[cur] > pivot){
                swap(array,cur,--more);
            }else {
                cur++;
            }
        }
        range[0] = less + 1;
        range[1] = more - 1;
        return  range;
    }
    public static  int medianOfMedians1(int[]arr,int L ,int R){
        int n = (R - L +1)% 5 == 0 ? 0 : 1;
        int groupNumber = (R- L + 1)/ 5 + n ;
        //每个分数组的中间值组成的新数组
        int [] group = new int[groupNumber];
        int index = 0;
        int gl = L ;
        int gr = L;
        //对分组的数组排序,取出中间值
        while (gl <= R){
            gr = Math.min(gl + 4,R);
            System.out.println(gl + " " + gr);
            group[index++] = getMedian(arr,gl,gr);
            gl = gr + 1;
        }
       return  bfprt(group,0,groupNumber - 1 ,groupNumber / 2);
    }
    public static int medianOfMedians(int[] arr, int L, int R) {
        int size = R - L + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] mArr = new int[size / 5 + offset];
        System.out.println(Arrays.toString(arr) + L + "  "+ R);
        for (int team = 0; team < mArr.length; team++) {
            int teamFirst = L + team * 5;
            System.out.println(teamFirst + " " + Math.min(R, teamFirst + 4) );
            // L ... L + 4
            // L +5 ... L +9
            // L +10....L+14
            mArr[team] = getMedian(arr, teamFirst, Math.min(R, teamFirst + 4));
        }
        // marr中，找到中位数
        // marr(0, marr.len - 1,  mArr.length / 2 )
        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }
    public static int getMedian(int[] arr, int L, int R) {
        insertSort(arr, L, R);
        return arr[(L + R) / 2];
    }
    public static  void insertSort(int[]arr,int L,int R){
        for(int i = L + 1; i <= R;i++){
            for(int j = i ; j > L ;j--){
                if(arr[j] < arr[j - 1]){
                    swap(arr,j ,j - 1);
                }
            }
        }
    }

//    public static void insertSort(int[] arr, int L, int R) {
//        for (int i = L + 1; i <= R; i++) {
//            for (int j = i - 1; j >= L && arr[j] > arr[j + 1]; j--) {
//                swap(arr, j, j + 1);
//            }
//        }
//    }
    public static void swap(int []arr,int i ,int j){
        int k = arr[i];
        arr[i] = arr[j];
        arr[j] = k;
    }
    public static  int findKth(int[]arr,int k){
        Arrays.sort(arr);
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
