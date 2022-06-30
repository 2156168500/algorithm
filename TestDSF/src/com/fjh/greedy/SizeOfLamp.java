package com.fjh.greedy;

/**
 * 给定一个字符串str，只由‘X’和‘.’两种字符构成。
 * ‘X’表示墙，不能放灯，也不需要点亮
 * ‘.’表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 */
public class SizeOfLamp {
    public static int numOfLamp(String str){
        int i = 0;
        int size = 0;
        while (i < str.length()){
            if(str.charAt(i) == 'x'){
                i++;
            }else{
                size++;
                if(i == str.length()){
                    break;
                }
                if(str.charAt(i + 1) == 'x'){
                    i = i + 2;
                }else{
                    i = i + 3;
                }

            }
        }
       return size;
    }

    public static void main(String[] args) {
        String s = "x...............x";
        System.out.println(numOfLamp(s));
    }
}
