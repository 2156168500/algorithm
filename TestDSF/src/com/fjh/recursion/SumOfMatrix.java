package com.fjh.recursion;

/**
 * 给定一个二维数组matrix，一个人必须从左上角出发，最后到达右下角
 * 沿途只可以向下或者向右走，沿途的数字都累加就是距离累加和
 * 返回最小距离累加和
 */
public class SumOfMatrix {
    public static int sumOfMatrix(int[][]arr){
        if(arr == null){
            return 0;
        }
       // return process2(arr,arr.length - 1,arr[0].length - 1);
        return process(arr,0,0);
    }
    public static int process(int[][]arr,int x,int y){
        if(x < 0 || x == arr.length || y < 0 || y == arr[0].length){
            return 0;
        }
        if(x == arr.length - 1 && y == arr[0].length - 1){
            return arr[x][y];
        }
        if(x == arr.length - 1){
            return process(arr,x,y+ 1)+arr[x][y];
        }
        if(y == arr[0].length - 1){
            return process(arr,x +1,y)+arr[x][y];
        }
        int p1 = arr[x][y]+process(arr,x,y + 1);
        int p2 = arr[x][y]+process(arr,x + 1, y);
        return  Math.min(p1,p2);
    }
public static int process2(int[][]arr,int x,int y){
        if(x == 0 && y == 0){
            return arr[x][y];
        }
        if( x == 0){
            return arr[x][y]+process2(arr,x ,y - 1);
        }
        if(y == 0){
            return arr[x][y]+ process2(arr,x - 1,y );
        }
        int p1 = arr[x][y] + process2(arr,x - 1,y);
        int p2= arr[x][y] + process2(arr,x ,y - 1);
        return  Math.min(p1,p2);
}
public static int dp1(int arr[][]){
        if(arr == null || arr.length == 0){
            return  0;
        }
        int row = arr.length;
        int col = arr[0].length;
        int[][]dp = new int[row][col];
        dp[0][0] = arr[0][0];
//        for(int i = 1 ; i < col ; i++){
//            dp[0][i] = dp[0][i - 1]+arr[0][i];
//        }
//        for(int i = 1 ; i < row ; i++){
//            dp[i][0] = dp[i - 1][0] + arr[i][0];
//        }
    for (int i = 1; i < row; i++) {
        dp[i][0] = dp[i - 1][0] + arr[i][0];
    }
    for (int j = 1; j < col; j++) {
        dp[0][j] = dp[0][j - 1] + arr[0][j];
    }
        for(int x = 1; x<row ;x++){
            for (int y = 1; y <col ; y++){
                int p1 = arr[x][y] +dp[x - 1][y];
                int p2= arr[x][y] + dp[x][y - 1];
                dp[x][y] = Math.min(p1,p2);
            }
        }
        return dp[row - 1][col - 1];
}

public static int dp2(int[][]arr){
    if(arr == null || arr.length == 0){
        return  0;
    }
    int row = arr.length;
    int col = arr[0].length;
    int[]dp = new int[col];
    dp[0] = arr[0][0];
    for(int i = 1 ; i < col ; i++){
        dp[i] = dp[i - 1] + arr[0][i];
    }
    for(int i = 1 ;i < row ; i++){
        for(int j = 0;j < col; j++){
            if(j == 0){
                dp[j] = dp[j] + arr[i][j];
            }else{
                dp[j] = Math.min(dp[j],dp[j - 1])+arr[i][j];
            }
        }
    }
    return dp[col - 1];
}


















    public static int minPathSum1(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = m[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + m[i][0];
        }
        for (int j = 1; j < col; j++) {
            dp[0][j] = dp[0][j - 1] + m[0][j];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + m[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }

    public static int minPathSum2(int[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            return 0;
        }
        int row = m.length;
        int col = m[0].length;
        int[] dp = new int[col];
        dp[0] = m[0][0];
        for (int j = 1; j < col; j++) {
            dp[j] = dp[j - 1] + m[0][j];
        }
        for (int i = 1; i < row; i++) {
            dp[0] += m[i][0];
            for (int j = 1; j < col; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + m[i][j];
            }
        }
        return dp[col - 1];
    }

    // for test
    public static int[][] generateRandomMatrix(int rowSize, int colSize) {
        if (rowSize < 0 || colSize < 0) {
            return null;
        }
        int[][] result = new int[rowSize][colSize];
        for (int i = 0; i != result.length; i++) {
            for (int j = 0; j != result[0].length; j++) {
                result[i][j] = (int) (Math.random() * 100);
            }
        }
        return result;
    }

    // for test
    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int rowSize = 10;
        int colSize = 10;
        int[][] m = generateRandomMatrix(rowSize, colSize);
        System.out.println(minPathSum1(m));
        System.out.println(minPathSum2(m));
        System.out.println(sumOfMatrix(m));
        System.out.println(dp1(m));
        System.out.println(dp2(m));

    }
}
