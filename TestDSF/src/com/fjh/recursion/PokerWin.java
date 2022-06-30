package com.fjh.recursion;
/**
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌
 * 玩家A和玩家B都绝顶聪明
 * 请返回最后获胜者的分数。
 */
public class PokerWin {

    public static int pokerWin1(int arr[]){
        int first = f(arr,0, arr.length - 1);
        int second = g(arr,0 ,arr.length - 1);
        return Math.max(first,second);
    }

    /**
     * 选手是先手的时候的时候获得的最大的分值
     * @param arr 扑克数组
     * @param L 左区间
     * @param R 右区间
     * @return 在这个区间上选手获得的最大分值
     */
    public static int f(int[]arr,int L ,int R){
        if(L == R){
            return arr[L];
        }
        int p1 = arr[L]+g(arr,L+1,R);
        int p2 = arr[R]+g(arr,L,R - 1);
        return Math.max(p1,p2);
    }
    public static int g(int[]arr,int L,int R){
        if(L == R){
            return 0;
        }
        int p1 = f(arr,L +1,R);
        int p2 = f(arr, L,R -1);
        return Math.min(p1,p2);
    }

    public static int pokerWin2(int arr[]){
        int N = arr.length;
        int [][] fMap = new int[N][N];
        int [][] gMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fMap[i][j] = -1;
                gMap[i][j] = -1;
            }
        }
        int first = f2(arr,0, N - 1,fMap,gMap);
        int second = g2(arr,0 ,N - 1,fMap,gMap);
        return Math.max(first,second);
    }

    /**
     * 选手是先手的时候的时候获得的最大的分值
     * @param arr 扑克数组
     * @param L 左区间
     * @param R 右区间
     * @return 在这个区间上选手获得的最大分值
     */
    public static int f2(int[]arr,int L ,int R,int[][]fMap,int[][]gMap){
        if(fMap[L][R] != -1){
            return fMap[L][R];
        }
        if(L == R){
            fMap[L][R] = arr[L];
            return arr[L];
        }
        int p1 = arr[L]+g2(arr,L+1,R,fMap,gMap);
        int p2 = arr[R]+g2(arr,L,R - 1,fMap,gMap);
        int ans = Math.max(p1,p2);
        fMap[L][R] = ans;
        return ans;
    }
    public static int g2(int[]arr,int L,int R,int[][]fMap,int[][]gMap){
        if(gMap[L][R] != -1){
            return gMap[L][R];
        }
        if(L == R){
            gMap[L][R] = 0;
            return 0;
        }
        int p1 = f2(arr,L +1,R,fMap,gMap);
        int p2 = f2(arr, L,R -1,fMap,gMap);
        int ans = Math.min(p1,p2);
        gMap[L][R] =ans;
        return ans;
    }

    /**
     * 顺序动态规划
     */
    public static int pokerWin3(int arr[]){
        int N = arr.length;
        int [][] fMap = new int[N][N];
        int [][] gMap = new int[N][N];
       for(int i = 0; i < N ;i++){
           fMap[i][i] = arr[i];
           gMap[i][i] = 0;
       }
       for(int i = 1; i < N; i++){
           int L = 0;
           int R = i;
           while (R < N){
               int p1 = arr[L] + gMap[L + 1][R];
               int p2 = arr[R] + gMap[L][R - 1];
               fMap[L][R] = Math.max(p1,p2);
               p1 = fMap[L + 1][R];
               p2 = fMap[L][R - 1];
               gMap[L][R] = Math.min(p1,p2);
               L++;
               R++;
           }
       }
       return Math.max(fMap[0][N - 1],gMap[0][N -1]);
    }
    public static void main(String[] args) {
        int []arr = {100,100,200,50,100};
        System.out.println(pokerWin1(arr));
        System.out.println(pokerWin2(arr));
        System.out.println(pokerWin3(arr));
    }
}
