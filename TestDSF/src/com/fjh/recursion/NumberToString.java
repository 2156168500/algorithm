package com.fjh.recursion;

public class NumberToString {
    public static int solve (String nums) {
        if(nums == null){
            return 0;
        }
        char [] str = nums.toCharArray();
       return process(str,0);
    }

    /**
     *数字字符串转为字符字符串,迪前面i - 1 个位置已经转换结束,从index位置开始有多少种转换的情况
     */
    public static int process(char[]str,int index){
        if(index == str.length){
            return  1;
        }
        if(str[index] == '0'){
            return  0 ;
        }
        //index 没有单独面对0
        int p1 = process(str,index + 1);
        int p2 = (index + 1 < str.length &&(str[index] -'0') * 10 + (str[index + 1] - '0') < 27 ) ? process(str,index + 2) : 0;
        return p1 + p2 ;
    }
    public static int solve1(String nums) {
        if(nums == null){
            return 0;
        }
        char [] str = nums.toCharArray();
        int N = str.length;
        int [] dp = new int[N+ 1];
        dp[N] = 1;
        for (int i = N - 1 ; i >= 0 ; i--){
            if (str[i] != '0') {
                int ways = dp[i + 1];
                if (i + 1 < str.length && (str[i] - '0') * 10 + str[i + 1] - '0' < 27) {
                    ways += dp[i + 2];
                }
                dp[i] = ways;
            }
        }
        return dp[0];
    }
    public static void main(String[] args) {
        solve1("31717126241541717");
        System.out.println(solve1("31717126241541717"));
    }
}
