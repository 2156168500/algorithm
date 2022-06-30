package com.fjh.kmp;

public class Kmp {
    public static int kmp(String str1,String str2){
        if(str1 == null || str2 == null || str1.length() < 1 || str2.length() > str1.length()){
            return  -1;
        }
        //模式串
        char[]s1  = str1.toCharArray();
        //目标串
        char[]s2 = str2.toCharArray();
        int x = 0;//目前模式串比配到的位置
        int y = 0;//目前目标串匹配到的位置
        int []next = getNextArr(s2);//获取next数组
        while (x < s1.length && y < s2.length){
            if(s1[x] == s2[y]){//如果目标串的位置和模式串的位置匹配那么,继续向下进行匹配
                x++;
                y++;
            }else if(next[y] != -1){
                y = next[y];
            }else {
                x++;
            }
        }
        //如果y来到了目标串的末尾,那么就说明目标串在模式串中找到了所有的匹配
        //如果y没有来到目标串的末尾,则说明 x来到了模式串的末尾,证明,目标串没有匹配成功
        return y == s2.length ? x - y : -1;
    }
    public static int[]getNextArr(char[]subStr) {
        if (subStr == null || subStr.length == 0) {
            return new int[]{-1};
        }
        int[] next = new int[subStr.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;//要求相等的最大前后缀长度的位置
        int cn = 0;//表示当前要去对比的位置.初始值为0 ,是因为i从2开始计算.而要得到2 位置的结果 要上1位置和0位置进行比较
        while (i < subStr.length){
            if(subStr[i - 1] == subStr[cn]){
                next[i] = ++cn;
            }else if(cn > 0){
               cn =  next[cn];
            }else{
                next[i++] = 0;
            }
        }
        return next;
    }
}
