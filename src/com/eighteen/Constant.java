package com.eighteen;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * 常量类
 */
public class Constant {
    //游戏模式4X4或5X5
    public static int ACCOUNT =4;
    public static int SIZE =100;
    //软件宽高
    public static final int width=550;
    public static final int height=500;
    //获取屏幕宽和高
    public static int w= Toolkit.getDefaultToolkit().getScreenSize().width;
    public static int h=Toolkit.getDefaultToolkit().getScreenSize().height;
    //100像素的路径和图片数组
    public static String loc100;
    public static BufferedImage img100[]=new BufferedImage[12];
    //80像素的路径和图片数组
    public static String loc80;
    public static BufferedImage img80[]=new BufferedImage[12];
    public static int loc[][]=new int[SIZE][SIZE];
    //当前是否选中100像素的图片
    public static boolean isImg100=true;
    //是否继续玩到4096
    public static boolean isContinue=false;

    public static boolean change;
    //计算游戏步数
    public static int count=0;
    //计算游戏分数
    public static int grade=0;
    public static boolean isOver =false;

}
