package com.fjh.monotonousStack;

/**
 * 给你一个 m x n 的二进制矩阵 mat ，请你返回有多少个 子矩形 的元素全部都是 1 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：mat = [[1,0,1],[1,1,0],[1,1,0]]
 * 输出：13
 * 解释：
 * 有 6 个 1x1 的矩形。
 * 有 2 个 1x2 的矩形。
 * 有 3 个 2x1 的矩形。
 * 有 1 个 2x2 的矩形。
 * 有 1 个 3x1 的矩形。
 * 矩形数目总共 = 6 + 2 + 3 + 1 + 1 = 13 。
 *
 * 示例 2：
 *
 * 输入：mat = [[0,1,1,0],[0,1,1,1],[1,1,1,0]]
 * 输出：24
 * 解释：
 * 有 8 个 1x1 的子矩形。
 * 有 5 个 1x2 的子矩形。
 * 有 2 个 1x3 的子矩形。
 * 有 4 个 2x1 的子矩形。
 * 有 2 个 2x2 的子矩形。
 * 有 2 个 3x1 的子矩形。
 * 有 1 个 3x2 的子矩形。
 * 矩形数目总共 = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24 。
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-submatrices-with-all-ones
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumSubmat {
    public  static int  numSubmat(int[][] mat) {
        if(mat == null || mat.length == 0){
            return  0;
        }
        int max = 0;
        int []arr = new int[mat[0].length];
        for (int i = 0; i < mat.length ; i++){
            for (int j = 0; j < mat[0].length ; j++){
                arr[j] = mat[i][j] == 0 ? 0 :1 + arr[j];
            }
            max += maxOfSub(arr);
        }
        return max;
    }
    public static int maxOfSub(int []heights){
        if(heights == null || heights.length == 0 ){
            return  0;
        }
        int []stack = new int[heights.length];
        int peek = -1;
        int max = 0;
        for (int i = 0;  i < heights.length ; i++){
            while (peek != -1 && heights[stack[peek]] >= heights[i]){

               if( heights[stack[peek]] == heights[i]){
                   peek --;
                   break;
               }
               int cur = stack[peek--];
                int right = i;
                int left = peek == -1  ? -1 : stack[peek];
                int maxHeight= 0;
                if(left == -1){
                    maxHeight= heights[right];
                }else{
                    maxHeight = Math.max(heights[left],heights[right]);
                }
                int a = left == -1 ? right : right - left - 1;
                if(a != heights[cur]){
                    break;
                }
                int n = heights[cur] - maxHeight;
                int temp = n * (a *(a + 1) /2);
                max += temp;
            }
            stack[++peek] = i;
        }
        while (peek != -1){
            int cur = stack[peek--];
            int right = heights.length ;
            int left = peek == -1 ? -1 : stack[peek];
            int maxHeight= left == -1 ? 0 : heights[left];
            int n  = heights[cur] - maxHeight;
            int a = left == -1 ?  right : right - left - 1;
            if(a != heights[cur]){
                break;
            }
            int temp = n * (a *(a + 1) /2);
            max += temp;
        }
        return  max;
    }

    public static void main(String[] args) {
        int [][]arr= {{0,1,1,0},{0,1,1,1},{1,1,1,0}};
        int [][]arr2 = {{1,1},{1,1}};
        int[][]arr3 = {{1,1}};
       // int max = maxOfSub(arr);
     //   int max2 = maxOfSub(arr3);
        int max = numSubmat(arr2);
        System.out.println(max);
    }

}
