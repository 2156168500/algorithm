package com.fjh.tree;

public class Origami {
    public static void ori(int N){
        if(N == 0){
            return ;
        }
        boolean down = true;
        progress(1,N,down);
    }
    public  static void progress(int i,int N,boolean down){
        if(i > N){
            return ;
        }
        progress(i+1,N,true);
        System.out.print(down?"凹":"凸");
        progress(i+1,N,false);
    }

    public static void main(String[] args) {
        ori(3);

    }
}
