package com.fjh.Test;

import java.util.Scanner;

public class Day2Main2 {
    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);
        while (scanner.hasNext()){
            String string = scanner.nextLine();
            String[]strings = string.split(" ");
            StringBuilder builder = new StringBuilder();
            for (int i = strings.length - 1;i>=0 ; i++){
                System.out.println(strings[i]+' ');
            }
        }

    }
}
