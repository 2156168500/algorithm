package com.fjh.greedy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 * 给你每一个项目开始的时间和结束的时间
 * 你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
 * 返回最多的宣讲场次。
 */
public class Meet {
    public static class meet {
        public int start;
        public int end;
        public meet(int s,int e){
            start = s;
            end = e;

    }
    public static int sizeOfMeet(int[] start, int[] end) {
            if(start == null || start.length == 0){
                return 0;
            }
        List<meet>list = new ArrayList<>();
            for (int i = 0;i < start.length;i++){
                list.add(new meet(start[i],end[i]));
            }
            list.sort(new Comparator<meet>() {
                @Override
                public int compare(meet o1, meet o2) {
                  return o1.end - o2.end;
                }
            });
            int ans = 0;
            int nextBegin = 0;
            for (int i = 0;i < list.size();i++){
                if(list.get(i).start >= nextBegin){
                    ans++;
                    nextBegin = list.get(i).end;
                }
            }
            return ans;
    }
}
}
