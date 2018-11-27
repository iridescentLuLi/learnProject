package com.mr.draw;

import java.awt.*;

public class DrawPictureCanvas extends Canvas {
    private Image image = null; // 创建画板中的图片对象

    public void setImage (Image image){
        this.image = image;         //成员变量赋值
    }

    public void paint (Graphics g){
        g.drawImage(image, 0, 0, null); //observer：随着更多的图像可用或者到了绘制动画另一帧的时候，
                                                        // 加载图像的进程将通知指定的图像观察者。
                                                        //返回：如果图像像素仍在更改，则返回 false；否则返回 true。
    }

    public void update(Graphics g){
        paint(g);
    }
}
