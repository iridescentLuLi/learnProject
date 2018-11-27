package com.mr.draw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class DrawPictureFrame extends JFrame {
    BufferedImage image = new BufferedImage(570, 390, BufferedImage.TYPE_INT_BGR);
    //BufferedImage生成的图片在内存里有一个图像缓冲区，利用这个缓冲区我们可以很方便的操作这个图片，通常用来做
    // 图片修改操作如大小变换、图片变灰、设置图片透明或不透明等。
    Graphics gs = image.getGraphics();
    //调用repaint()方法确实可以完成很多java绘图需求，但是当需要灵活得到Graphics对象时，就必须用getGraphics
    // ()获取了
    Graphics2D g = (Graphics2D) gs;
    DrawPictureCanvas canvas = new DrawPictureCanvas();
    Color foreColor = Color.BLACK;
    Color backgroundColor = Color.white;
    int x = -1;//鼠标绘制初始位置
    int y = -1;
    boolean rubber = false;//橡皮的东西
    /**定义一些按钮工具栏**/
    private JToolBar toolBar;
    private JButton eraserButton;
    private JToggleButton strokeButton1;
    private JToggleButton strokeButton2;
    private JToggleButton strokeButton3;
    //JToggleButton保证同时只有一个按钮处于选中状态并不再弹起
    //JButton按钮每次单击之后都会恢复自动弹起
    private JButton backgroundButton;
    private JButton foregroundButton;
    private JButton clearButton;
    private JButton saveButton;
    private JButton shapeButton;


    /**构造方法**/
    public DrawPictureFrame() {
        setResizable(false); // 窗体不能改变大小；
        setTitle("DrawPicture"); // 标题
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //窗体关闭则停止程序
        setBounds( 500, 100, 574, 460);//位置大小

        init();
        addListener();
    }

    /**组件初始化**/
    private void init(){
        g.setColor(backgroundColor);
        g.fillRect(0, 0, 570, 390);
        g.setColor(foreColor);
        canvas.setImage(image);
        getContentPane().add(canvas);

        toolBar = new JToolBar();
        getContentPane().add(toolBar, BorderLayout.NORTH);
        //JFrame 不是一个容器，它只是一个框架。
        //用getContentPane()方法获得JFrame的内容面板，
        //再对其加入组件:frame.getContentPane().add(childComponent)

        saveButton = new JButton("save");//初始化按钮
        toolBar.add(saveButton);//添加到toolbar
        toolBar.addSeparator();//添加分隔


        /**JToggleButton按了没用，不处于选中状态？**/
        strokeButton1 = new JToggleButton("Thin", true);
        toolBar.add(strokeButton1);
        strokeButton2 = new JToggleButton("Thicker");
        toolBar.add(strokeButton2);
        strokeButton3 = new JToggleButton("Thick");
        toolBar.add(strokeButton3);
        ButtonGroup strokeGroup = new ButtonGroup();
        strokeGroup.add(strokeButton1);
        strokeGroup.add(strokeButton2);
        strokeGroup.add(strokeButton3);
        toolBar.addSeparator();

        

    }

    /**鼠标画笔操作**/
    private void addListener(){
        //鼠标按键时候的操作
        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            //鼠标按键，拖拽
            public void mouseDragged(final MouseEvent e){
                if (x > 0 && y > 0){
                    if (rubber){
                        g.setColor(backgroundColor);
                        g.fillRect(x, y, 10, 10);//鼠标划过位置橡皮
                    }else {
                        g.drawLine(x, y, e.getX(), e.getY());//鼠标划过的位置划线
                    }
                }
                x = e.getX();
                y = e.getY();
                canvas.repaint();
            }
        });

        //按键抬起时候的操作，恢复成-1
        canvas.addMouseListener(new MouseAdapter() {
            public void mouseReleased(final  MouseEvent arg0){
                x = -1;
                y = -1;
            }
        });
    }

    public static void main(String[] args){
        DrawPictureFrame frame = new DrawPictureFrame();
        frame.setVisible(true); //窗体可见
    }
}