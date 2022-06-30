package com.fjh.heapsort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 题目描述:
 * 给定很多线段，每个线段都有两个数[start, end]，
 * 表示线段开始位置和结束位置，左右都是闭区间
 * 规定：
 * 1）线段的开始和结束位置一定都是整数值
 * 2）线段重合区域的长度必须>=1
 * 返回线段最多重合区域中，包含了几条线段
 * _______________________________________________
 *
 * 题解:
 * 1)转换问题,要求解线段的重合问题,可以转换为求解以这些线段中,某个线段的开始位置为左边界的重合区域,有多少线段通过
 * 2)对线段的开头按照升序排序,(为了确保在求解以某个线段的开始作为重合区域的左边界时所有的线段都被计算过)
 * 3)建立小根堆
 * 4)遍历数组,弹出 <= 当前线段开头的数,(这些线段不可能串过当前线段)
 * 5)堆的个数就是一当前线段的左边界为重合区域左边界的线段的总数
 * ()
 */


public class MaxCover {
    public static class Line{
        public int start;
        public int end;
        public Line(int start,int end){
            this.start = start;
            this.end = end;
        }
    }
    public static int maxCover1(int[][] lines) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }
        int cover = 0;
        for (double p = min + 0.5; p < max; p += 1) {
            int cur = 0;
            for (int i = 0; i < lines.length; i++) {
                if (lines[i][0] < p && lines[i][1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }
        return cover;
    }
    public static int maxCover(int[][]m){
        Line[] lines = new Line[m.length];
        for (int i = 0; i < m.length; i++) {
            lines[i] = new Line(m[i][0], m[i][1]);
        }
        //1.对线段的开始位置进行升序排序
        Arrays.sort(lines, new Comparator<Line>() {

            @Override
            public int compare(Line o1, Line o2) {
                return o1.start - o2.start;
            }
        });
        //建立小根堆
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        //遍历数组求值
        int max = 0;
        for(int i = 0;i < lines.length ;i++ ){
            while (!minHeap.isEmpty() && lines[i].start >= minHeap.peek()){
                minHeap.poll();
            }
            minHeap.add(lines[i].end);
            max = Math.max(max, minHeap.size());
        }
        return max;
    }
    // for test
    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

    public static class StartComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }

    }

    public static void main(String[] args) {

        Line l1 = new Line(4, 9);
        Line l2 = new Line(1, 4);
        Line l3 = new Line(7, 15);
        Line l4 = new Line(2, 4);
        Line l5 = new Line(4, 6);
        Line l6 = new Line(3, 7);

        // 底层堆结构，heap
        PriorityQueue<Line> heap = new PriorityQueue<>(new StartComparator());
        heap.add(l1);
        heap.add(l2);
        heap.add(l3);
        heap.add(l4);
        heap.add(l5);
        heap.add(l6);

        while (!heap.isEmpty()) {
            Line cur = heap.poll();
            System.out.println(cur.start + "," + cur.end);
        }

        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = maxCover1(lines);
            int ans2 = maxCover(lines);
            if (ans1 != ans2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
    }
}
