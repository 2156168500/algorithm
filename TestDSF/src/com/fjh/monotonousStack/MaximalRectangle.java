package com.fjh.monotonousStack;

/**
 * \给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 *
 *
 *
 * 示例 1：
 *
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：6
 * 解释：最大矩形如上图所示。
 *
 * 示例 2：
 *
 * 输入：matrix = []
 * 输出：0
 *
 * 示例 3：
 *
 * 输入：matrix = [["0"]]
 * 输出：0
 *
 * 示例 4：
 *
 * 输入：matrix = [["1"]]
 * 输出：1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximal-rectangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if(matrix == null || matrix.length == 0){
            return  0;
        }
        //以每一层作为矩形的底,看看能有多少的1
        int arr[] = new int[matrix[0].length];//表示当前的底
        int max = 0;
        for (int i = 0; i < matrix.length ; i++){
            for (int j = 0; j < matrix[0].length ; j++){
                arr[j] = matrix[i][j] == '0' ? 0 : 1 + arr[j];
            }
            max =  Math.max(max,largestRectangleArea(arr));
        }
        return  max;
    }
    public int largestRectangleArea(int[] heights) {
        if(heights == null || heights .length == 0){
            return  0;
        }
        //1.求出每个位置左边最近比它自己小的和右边最近比它自己小的
        int[]stack = new int[heights.length];
        int peek = -1;
        int maxS = Integer.MIN_VALUE;
        for (int i = 0 ; i <  heights.length ; i++){
            while(peek!=  -1 && heights[stack[peek]] >= heights[i]){
                int cur = stack[peek--];
                int right = i;
                int left = peek == -1 ? -1 : stack[peek];
                //得到左最小和右最下后,因为要求,矩形的最大面经,那么就以当前矩形的高为整个矩形的高
                int a = left == -1 ? right : right -left - 1;
                maxS = Math.max(maxS,a*heights[cur]);
            }
            stack[++peek] = i;
        }
        while (peek != -1){
            int cur = stack[peek--];
            int right = heights.length - 1;
            int left = peek == -1 ? -1 : stack[peek];
            int a  =  left == -1 ? right + 1: right - left;
            maxS = Math.max(maxS,a *  heights[cur]);
        }
        return maxS;
    }

}
