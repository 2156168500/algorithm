package com.fjh.Test;

import java.util.ArrayList;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String a = scanner.nextLine();
            String b = scanner.nextLine();
            char[]chars1 =a.toCharArray();
            ArrayList<Character>list = new ArrayList<Character>();
            ArrayList<Character>list2 = new ArrayList<Character>();
            for (char c: chars1){
                list.add(c);
            }
            char[]chars2  = b.toCharArray();
            for (char c:chars2){
                list2.add(c);
            }
            for (char c:chars1){
                if(list2.contains(c)){
                  int i = list.indexOf(c);
                  list.remove(i);
                }
            }
          a="";
            for (char c:
                 list) {
               a+=c;
            }
            System.out.println(a);
        }
    }
}
