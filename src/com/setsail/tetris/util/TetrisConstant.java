package com.setsail.tetris.util;

import java.awt.*;
import java.util.Random;

public class TetrisConstant {
    public static final int LEFT_CONTAINER_WIDTH = 4;
    public static final int LEFT_CONTAINER_HEIGHT = 4;
    public static final int RIGHT_CONTAINER_X = 0;
    public static final int RIGHT_CONTAINER_Y = 0;
    public static final int RIGHT_CONTAINER_WIDTH = 15;
    public static final int RIGHT_CONTAINER_HEIGHT = 20;
    public static final int CELL_HEIGHT = 20;
    public static final int CELL_WIDTH = 20;
    public static final long SLEEP_TIME = 100;

    private static Random random = new Random();
    public static final Color COLOR_RED = new Color(0xFF0000);//红色
    public static final Color COLOR_ORANGE = new Color(0xFFA500);//橙色
    public static final Color COLOR_YELLOW= new Color(0xFFFF00);//黄色
    public static final Color COLOR_GREEN = new Color(0x008000);//绿色
    public static final Color COLOR_YELLOWGREEN  = new Color(0x9ACD32);//黄绿
    public static final Color COLOR_BLUE = new Color(0x0000FF);//蓝色
    public static final Color COLOR_PURPLE= new Color(0x800080);//紫色
    public static final Color COLOR_BROWN = new Color(0xA52A2A);//棕色
    public static final Color COLOR_PINK = new Color(0xFFC0CB);//粉红
    public static final Color COLOR_DEEPPINK  = new Color(0xFF1493);//深粉红
    public static final Color COLOR_GRAY = new Color(0x808080);//灰色
    public static final Color COLOR_LIGHTGRAY = new Color(0xDCDCDC);//浅灰色
    public static final Color COLOR_DIMGRAY = new Color(0x696969);//暗灰色
    public static final Color COLOR_WHITE = new Color(0xFFFFFF);//白色
    public static final Color COLOR_BLACK = new Color(0x000000);//黑色

    public static final int LEFT_CONTAINER_X = 0;
    public static final int LEFT_CONTAINER_Y = 0;
    public static final int DOWN_SPEED = 6;

    private static final Color[] BRICK_COLORS = {
            COLOR_RED,
            COLOR_YELLOW,
            COLOR_PINK,
            COLOR_ORANGE,
            COLOR_GREEN,
            COLOR_BLUE,
            COLOR_PURPLE,
            COLOR_YELLOWGREEN,
            COLOR_DEEPPINK,
            COLOR_BROWN};
    /**随机返回一种颜色*/
    public static Color getRandomColor()
    {
        int colorIndex = random.nextInt(10);
        return BRICK_COLORS[colorIndex];
    }
}
