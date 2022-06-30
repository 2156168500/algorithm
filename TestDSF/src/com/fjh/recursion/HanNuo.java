package com.fjh.recursion;

public class HanNuo {
    public static void main(String[] args) {
        hanNuo(6);
    }
    public static void hanNuo(int N){
        move(N,"left","right","mind");

    }
    public static void move(int N,String from,String to,String other){
        if(N == 1){
            System.out.println(N +" from "+from +" to "+ to);
            return;
        }
        move(N-1,from,other,to);
        System.out.println(N +" from "+from +" to "+ to);
        move(N-1,other,to,from);
    }

}
