package com.fjh.Test;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class hhh extends JFrame implements ActionListener {
    static double h=0;static double y,x,f=0; static long s,k=0;
    static String d="";
    static int jia=0,jian,cheng,chu=0;
    JLabel j1;
    JButton jb[];
    JPanel jp1,jp2;



    public hhh(){
        j1=new JLabel();
        jp1=new JPanel();
        jp2=new JPanel();
        jb=new JButton[16];
        for(int i=0;i<16;i++)
        {
            jb[i]=new JButton(""+i);
            jb[i].addActionListener(this);

        }
        jp2.setLayout(new GridLayout(4,4));
        for(int i=0;i<16;i++)
        {	jp2.add(jb[i]);
        }
        jb[0].setActionCommand("9");jb[0].setText("9");
        jb[1].setActionCommand("8");jb[1].setText("8");
        jb[2].setActionCommand("7");jb[2].setText("7");
        jb[3].setActionCommand("/");jb[3].setText("/");
        jb[4].setActionCommand("6");jb[4].setText("6");
        jb[5].setActionCommand("5");jb[5].setText("5");
        jb[6].setActionCommand("4");jb[6].setText("4");
        jb[7].setActionCommand("*");jb[7].setText("*");
        jb[8].setActionCommand("3");jb[8].setText("3");
        jb[9].setActionCommand("2");jb[9].setText("2");
        jb[10].setActionCommand("1");jb[10].setText("1");
        jb[11].setActionCommand("-");jb[11].setText("-");
        jb[12].setActionCommand("0");jb[12].setText("0");
        jb[13].setActionCommand("C");jb[13].setText("C");
        jb[14].setActionCommand("=");jb[14].setText("=");
        jb[15].setActionCommand("+");jb[15].setText("+");


        this.add(jp2);
        this.add(jp1,BorderLayout.NORTH);
        jp1.add(j1);
        j1.setText("ºÂÊæÓîµÄ¼ÆËãÆ÷");
        jp1.setLayout(new FlowLayout(FlowLayout.RIGHT));
        j1.setFont(new Font("ËÎÌå",Font.PLAIN,40));
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }



    public static void main(String[] args) {

        new hhh();
        System.out.print(h);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jb[0])
        {	String i="9";
            k=Long.parseLong(k+i);y=(double)k;

            //i=e.getActionCommand();
            j1.setText(y+"");}

        if(e.getSource()==jb[1])
        {String i="8";
            k=Long.parseLong(k+i);y=(double)k;
//		y=Double.parseDouble(d+i);

            //i=e.getActionCommand();
            j1.setText(y+"");}

        if(e.getSource()==jb[2])
        {String i="7";
//		y=Double.parseDouble(d+i);
            k=Long.parseLong(k+i);y=(double)k;

            //i=e.getActionCommand();
            j1.setText(y+"");}

        if(e.getSource()==jb[4])
        {String i="6";
//		y=Double.parseDouble(d+i);
            k=Long.parseLong(k+i);y=(double)k;
            //i=e.getActionCommand();
            j1.setText(y+"");}

        if(e.getSource()==jb[5])
        {String i="5";
//		y=Double.parseDouble("y"+i);
            k=Long.parseLong(k+i);y=(double)k;
            //i=e.getActionCommand();
            j1.setText(y+"");}

        if(e.getSource()==jb[6])
        {String i="4";
//		y=Double.parseDouble("y"+i);
            k=Long.parseLong(k+i);y=(double)k;
            //i=e.getActionCommand();
            j1.setText(y+"");}

        if(e.getSource()==jb[8])
        {String i="3";
//		y=Double.parseDouble("y"+i);
            k=Long.parseLong(k+i);y=(double)k;
            //i=e.getActionCommand();
            j1.setText(y+"");}

        if(e.getSource()==jb[9])
        {String i="2";
//		y=Double.parseDouble("y"+i);
            k=Long.parseLong(k+i);y=(double)k;
            //i=e.getActionCommand();
            j1.setText(y+"");}

        if(e.getSource()==jb[10])
        {String i="1";
//		y=Double.parseDouble("y"+i);
            k=Long.parseLong(k+i);y=(double)k;
            //i=e.getActionCommand();
            j1.setText(y+"");}

        if(e.getSource()==jb[12])
        {String i="0";
//		y=Double.parseDouble("y"+i);

            k=Long.parseLong(k+i);y=(double)k;
            //i=e.getActionCommand();
            j1.setText(y+"");}
        if(e.getSource()==jb[15])
        {   j1.setText("+");

            if(jia==0&&jian==0&&cheng==0&&chu==0){h=y;}
            x=y;
            if(jia==1&&jian==0&&cheng==0&&chu==0){h=h+x;}
            if(jia==0&&jian==1&&cheng==0&&chu==0){h=h-x;}
            if(jia==0&&jian==0&&cheng==1&&chu==0){h=h*x;}
            if(jia==0&&jian==0&&cheng==0&&chu==1){h=h/x;}
            jia=1;jian=0;cheng=0;chu=0;y=0.0;s=0;k=0;
        }
        if(e.getSource()==jb[11])
        {   j1.setText("-");

            if(jia==0&&jian==0&&cheng==0&&chu==0){h=y;}
            x=y;
            if(jia==1&&jian==0&&cheng==0&&chu==0){h=h+x;}
            if(jia==0&&jian==1&&cheng==0&&chu==0){h=h-x;}
            if(jia==0&&jian==0&&cheng==1&&chu==0){h=h*x;}
            if(jia==0&&jian==0&&cheng==0&&chu==1){h=h/x;}
            jia=0;jian=1;cheng=0;chu=0;y=0.0;s=0;k=0;
        }
        if(e.getSource()==jb[15])
        {   j1.setText("+");

            if(jia==0&&jian==0&&cheng==0&&chu==0){h=y;}
            x=y;
            if(jia==1&&jian==0&&cheng==0&&chu==0){h=h+x;}
            if(jia==0&&jian==1&&cheng==0&&chu==0){h=h-x;}
            if(jia==0&&jian==0&&cheng==1&&chu==0){h=h*x;}
            if(jia==0&&jian==0&&cheng==0&&chu==1){h=h/x;}
            jia=1;jian=0;cheng=0;chu=0;y=0.0;s=0;k=0;
        }
        if(e.getSource()==jb[7])
        {   j1.setText("*");

            if(jia==0&&jian==0&&cheng==0&&chu==0){h=y;}
            x=y;
            if(jia==1&&jian==0&&cheng==0&&chu==0){h=h+x;}
            if(jia==0&&jian==1&&cheng==0&&chu==0){h=h-x;}
            if(jia==0&&jian==0&&cheng==1&&chu==0){h=h*x;}
            if(jia==0&&jian==0&&cheng==0&&chu==1){h=h/x;}
            jia=0;jian=0;cheng=1;chu=0;y=0.0;s=0;k=0;
        }if(e.getSource()==jb[15])
        {   j1.setText("+");

            if(jia==0&&jian==0&&cheng==0&&chu==0){h=y;}
            x=y;
            if(jia==1&&jian==0&&cheng==0&&chu==0){h=h+x;}
            if(jia==0&&jian==1&&cheng==0&&chu==0){h=h-x;}
            if(jia==0&&jian==0&&cheng==1&&chu==0){h=h*x;}
            if(jia==0&&jian==0&&cheng==0&&chu==1){h=h/x;}
            jia=1;jian=0;cheng=0;chu=0;y=0.0;s=0;k=0;
        }
        if(e.getSource()==jb[3])
        {   j1.setText("/");

            if(jia==0&&jian==0&&cheng==0&&chu==0){h=y;}
            x=y;
            if(jia==1&&jian==0&&cheng==0&&chu==0){h=h+x;}
            if(jia==0&&jian==1&&cheng==0&&chu==0){h=h-x;}
            if(jia==0&&jian==0&&cheng==1&&chu==0){h=h*x;}
            if(jia==0&&jian==0&&cheng==0&&chu==1){h=h/x;}
            jia=0;jian=0;cheng=0;chu=1;y=0.0;s=0;k=0;
        }

        if(e.getSource()==jb[13])//ÇåÁã
        {j1.setText("¼ÆËãÆ÷");
            jia=0;jian=0;cheng=0;chu=0;y=0.0;
            h=0.0;y=0.0;x=0.0;s=0;k=0;
        }
        if(e.getSource()==jb[14])//µÈÓÚ
        {    x=y;

            if(jia==1&&jian==0&&cheng==0&&chu==0){h=h+x;}
            if(jia==0&&jian==1&&cheng==0&&chu==0){h=h-x;}
            if(jia==0&&jian==0&&cheng==1&&chu==0){h=h*x;}
            if(jia==0&&jian==0&&cheng==0&&chu==1){h=h/x;}
            j1.setText(h+"");
            jia=0;jian=0;cheng=0;chu=0;
            y=h;s=0;k=0;
        }
    }

}



