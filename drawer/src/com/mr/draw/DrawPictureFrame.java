package com.mr.draw;

import javax.swing.*;

public class DrawPictureFrame extends JFrame {
    public DrawPictureFrame() {
        setResizable(false); // 窗体不能改变大小；
        setTitle("DrawPicture"); // 标题
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //窗体关闭则停止程序
        setBounds( 500, 100, 574, 460);//位置大小
    }

    public static void main(String[] args){
        DrawPictureFrame frame = new DrawPictureFrame();
        frame.setVisible(true); //窗体可见
    }
}

