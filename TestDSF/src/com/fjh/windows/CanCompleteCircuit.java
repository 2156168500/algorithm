package com.fjh.windows;

import java.util.LinkedList;

public class CanCompleteCircuit {
    public static int  canCompleteCircuit(int[] gas, int[] cost) {
        if(gas == null ||cost == null){
            return  -1;
        }
     boolean  []good = goodArr(gas,cost);
        for (int i = 0; i < good.length ; i++){
            if(good[i]){
                return i;
            }
        }
        return  -1;
    }
    public static boolean[]goodArr(int []gas,int[]cost){
        int N = gas.length;
        int []arr = new int[N];
        for (int i = 0; i < N ; i ++){
            arr[i] = gas[i] - cost[i];
        }
        int []sum = new int[N * 2 - 1];
        sum[0] = arr[0];
        for (int  i = 1; i <  sum.length ; i++){
            sum[i] = sum[i - 1] + arr[i % arr.length];
        }
         boolean[] ret = new boolean[N];
          int index  = 0;
        int L = 0;
        int R = 0;
        LinkedList<Integer> list = new LinkedList<>();
        while (R < sum.length){
            while (!list.isEmpty() && sum[list.peek()] >= sum[R]){
                list.pollLast();
            }
            list.addLast(R);
            if(R >= N - 1) {
                if(L == 0){
                    ret[index++] =sum[list.peekFirst()] < 0 ? false : true;
                }else{
                    ret[index++] = sum[list.peekFirst()] - sum[L - 1] < 0 ? false : true;
                }
                if(list.peekFirst()== L){
                    list.pollFirst();
                }
                L++;
            }
            R++;
        }

        return ret;
    }

    public static void main(String[] args) {
       int[] gas= {1,2,3,4,5}, cost = {3,4,5,1,2};
       canCompleteCircuit(gas,cost);
    }
}
