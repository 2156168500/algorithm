package com.fjh.Test;
import javax.swing.*;
public class Test {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame("图书管理系统");
        jFrame.setBounds(10,10,1000,1000);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JTextField inputName = new JFormattedTextField(10);
        JButton button = new JButton("按钮");
        JTextArea area = new JTextArea(5,10);
        jFrame.add(new JScrollPane(area));
        jFrame.add(button);
        jFrame.add(inputName);
        jFrame.validate();
        jFrame.setVisible(true);
    }
}
