package com.fjh.tree;
import java.util.LinkedList;
class Employee {
    public int happy;
    public LinkedList<Employee> subordinates;

    public Employee(int happy) {
        this.happy = happy;
        subordinates = new LinkedList<>();
    }
}
public class MaxHappy {

    public static int maxHappy(Employee head){
      Info ans = process(head);
      return  Math.max(ans.yes,ans.no);
    }
    public static class  Info{
        public int yes;
        public int no;

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }
    public  static Info process(Employee node){
        if(node == null){
            return new Info(0,0);
        }
        int yes = node.happy;
        int no = 0;
        for (Employee s:
             node.subordinates) {
           Info info = process(s);
           yes += info.no;
           no += Math.max(info.no, info.yes);
        }
        return new Info(yes,no);
    }
}

