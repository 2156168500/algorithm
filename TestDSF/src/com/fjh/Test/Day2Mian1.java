package com.fjh.Test;

import java.util.Scanner;

public class Day2Mian1 {
    public static void main(String[] args) {
        Scanner s =  new Scanner(System.in);
        while (s.hasNext()){
            int n = s.nextInt();
            int[]arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = s.nextInt();
            }
            if(n == 1){
                System.out.println(1);
            }
            int k = 0;
            boolean is =true;
            int index = 1;
            while (index < n){
                if(index + 1 < n){
                    boolean is1 = arr[index] > arr[index - 1];
                    boolean is2 = arr[index + 1]  > arr[index];
                    if(is1 != is2){
                        if(arr[index] > arr[index - 1] && arr[index] == arr[index + 1]){
                            continue;
                        }
                        if(arr[index] < arr[index - 1] && arr[index] == arr[index + 1]){
                            continue;
                        }
                        k++;
                        index += 2;
                    }else {
                        index++;
                    }
                }else{
                    is = false;
                    k++;
                    break;
                }
            }
            if(index == n&&is){
                k++;
            }
            System.out.println(k);
        }

    }

}
