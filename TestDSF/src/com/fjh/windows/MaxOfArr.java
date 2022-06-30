package com.fjh.windows;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

/**
 * 假设一个固定大小为W的窗口，依次划过arr，
 * 返回每一次滑出状况的最大值
 * 例如，arr = [4,3,5,4,3,3,6,7], W = 3
 * 返回：[5,5,5,4,6,7]
 */
public class MaxOfArr {
    public static int[] right(int []arr,int w){
        if(arr == null || arr.length < w){
            return  null;
        }
        int L = 0;
        int R= 0;
        int N = arr.length - w + 1;
        int []ans = new int[N];
        int index = 0;
        while (R <= arr.length&&L < arr.length){
          if(R >= w){
                int max = arr[L];
                for (int i = L ; i < R ; i++){
                    if(arr[i] > max){
                        max = arr[i];
                    }
                }
                ans[index++] = max;
                L++;
          }
          R++;
        }
        return  ans;
    }
    //滑动窗口发
    public static int[] maxOfArr(int []arr,int w){
        if(arr == null || arr.length < w){
            return  null;
        }
        int R = 0;
        int L = 0;
        int [] ans = new int[arr.length - w + 1];
        int index = 0;
       LinkedList<Integer> list = new LinkedList<>();
        while (R < arr.length){
            while (!list.isEmpty()&&arr[list.peekLast()]<= arr[R]){
                list.pollLast();
            }
            list.addLast(R);
            if(!list.isEmpty()&&R >= w - 1){
                ans[index++] = arr[list.peekFirst()];
                if( L == list.peekFirst() ){
                    list.pollFirst();
                }
                L++;
            }
            R++;
        }
        return ans;
    }

    //for test
    public static int[]randomArr(int max,int len){
        Random random  = new Random();
        int [] ret = new int[len];
        for(int i = 0; i < len ; i++){
            ret[i] = Math.abs(random.nextInt()%max);
        }
        return ret;
    }
public static boolean isSameArr(int []arr1,int []arr2){
        if(arr1 == null){
            return  arr2 == null ? true : false;
        }
        for(int i = 0; i < arr1.length;i++){
            if(arr1[i] != arr2[i]){
                return  false;
            }
        }
        return true;
}
    public static void main(String[] args) {
        int maxTime = 1000000;
        int max = 20;
        int len = 10;
        Random random = new Random();
        System.out.println("测试开始");
        for (int i = 0; i < maxTime ; i++){
            int [] arr =randomArr(max,len);
            int w = Math.abs(random.nextInt() % len - 3);
            int [] ans1 = right(arr,w);
            int [] ans2 =  maxOfArr(arr,w);
            if(!isSameArr(ans1,ans2)){
                System.out.println("false");
                System.out.println(Arrays.toString(arr));
                System.out.println(Arrays.toString(ans1));
                  System.out.println(Arrays.toString(ans2));
                return;
            }
        }
        System.out.println("通过测试");
    }
}
