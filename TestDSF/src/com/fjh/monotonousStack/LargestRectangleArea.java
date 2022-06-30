package com.fjh.monotonousStack;
//https://leetcode-cn.com/problems/largest-rectangle-in-histogram/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china

/**
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *
 *
 *
 * 示例 1:
 *
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 *
 * 示例 2：
 *
 * 输入： heights = [2,4]
 * 输出： 4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LargestRectangleArea {
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
