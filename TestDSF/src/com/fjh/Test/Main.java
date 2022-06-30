package com.fjh.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            ArrayList<Integer> list = new ArrayList<>();
            int n = scanner.nextInt();
            for (int i = 0; i < 3 * n; i++) {
                int num = scanner.nextInt();
                list.add(num);
            }
            System.out.println(maxLead(list));
        }
    }

    public static int maxLead(ArrayList<Integer> list) {
        list.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });
        int sum = 0;
        while (!list.isEmpty()) {
            int n = list.size();
            sum += list.get(n - 2);
            list.remove(n - 1);
            list.remove(n - 2);
            list.remove(0);
        }
        return sum;
    }
}
