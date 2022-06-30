package com.fjh.manacher;

public class Manacher {
    /**
     * 求一个字符串的子大回文子串的长度
     * @param string 要求的字符串
     * @return 最长回文子串的长度
     */
    public  static int manacher(String string){
        if(string == null || string.length() == 0){
            return 0;
        }
        /**
         * 原始串处理为处理串
         * 121
         * #1#2#1#
         */
        char[] str = manacherString(string);
        int [] pArr = new int[str.length];//回文半径数组
        int R = -1;//最右边回文右边界的下一个,就是不能再扩的位置
        int C = -1 ;//最右回文右边界对应的中心
        int max = 0;
        for(int i = 0; i < str.length; i++){
            /**
             *以目前的i为中心最小的回文半径是多少
             */
            pArr[i] = R > i ? Math.min(pArr[2 * C - i] ,R - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]])
                    pArr[i]++;
                else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
            max = Math.max(max,pArr[i]);
        }
        return max - 1;
    }

    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }
    // for test
    public static int right(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherString(s);
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < str.length && str[L] == str[R]) {
                L--;
                R++;
            }
            max = Math.max(max, R - L - 1);
        }
        return max / 2;
    }

    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            if (manacher(str) != right(str)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
