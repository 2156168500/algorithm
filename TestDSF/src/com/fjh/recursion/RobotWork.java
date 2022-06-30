package com.fjh.recursion;

public class RobotWork {
    /**
     * 一共有N个位置,机器人开始在start位置,要去aim初,要走k步,有几种走法
     * @param N N个位置
     * @param start 开始的位置
     * @param aim 目标位置
     * @param k 要走的步数
     * @return 返回可能的次数
     */
    public static int robotWork(int N,int start,int aim,int k){
        return process(start,k,aim,N);
    }
    public static int process(int cur,int result,int aim,int N){
        if(result == 0){
            return cur == aim ? 1 : 0;
        }
        if(cur == 1){
            return  process(2,result - 1,aim,N);
        }
        if(cur == N){
            return process(N-1,result - 1,aim,N);
        }
        return process(cur + 1,result - 1,aim,N) +
                process(cur -1 ,result -1,aim,N);
    }


    /**
     *
     *傻缓存方式优化
     */
    public static int robotWork2(int N,int start,int aim,int k){
        int path[][] = new int[N+1][k+1];
        for(int i = 0 ; i<=N; i++){
            for (int j = 0 ; j <= k; j++){
                path[i][j] = -1;
            }
        }
        return process2(start,k,aim,N,path);
    }
    public static int process2(int cur,int result,int aim,int N,int [][]path){
        if(path[cur][result] != -1){
            return path[cur][result];
        }
        if(result == 0){
            return  cur == aim ? 1  : 0;
        }
        int ans = 0;
        if(cur == 1){
           ans = process2(2,result - 1,aim,N,path);
        }else if(cur == N){
           ans = process2(N-1,result - 1,aim,N,path);
        }else{
             ans =  process2(cur + 1,result - 1,aim,N,path) +
                process2(cur -1 ,result -1,aim,N,path);
        }
        path[cur][result] = ans;
        return ans;
    }
    public static int robotWork3(int N,int start,int aim,int k){
        int path[][] = new int[N+1][k+1];
        path[aim][0] = 1;
        for(int result = 1; result <= k ; result++){
            path[1][result] = path[2][result - 1 ];
            for(int cur = 2; cur < N; cur++){
                path[cur][result] = path[cur -1][result - 1] + path[cur + 1][result - 1];
            }
            path[N][result] = path[N - 1][result - 1];
        }
        return path[start][k];
    }
    public static void main(String[] args) {
        int i = robotWork3(5,2,4,6);
        System.out.println(i);
    }
}
