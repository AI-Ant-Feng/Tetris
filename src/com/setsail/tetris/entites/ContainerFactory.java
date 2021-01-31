package com.setsail.tetris.entites;

import com.setsail.tetris.util.TetrisConstant;

import java.awt.*;

public abstract class ContainerFactory {

    /**
     * 抽象出子类共有的成员变量,由子类去初始化(即由子类决定样式).
     */
    protected Color backGroundColor;
    protected int locationX;
    protected int locationY;
    protected int width;//单位：格.每格20像素.
    protected int height;//单位：格.每格20像素.
    protected int[][] body;
    protected Color gridColor;


    /**
     * 设置容器背景颜色
     */
    protected void drawBackgroundColor(Graphics graphics){
        graphics.setColor(backGroundColor);
        graphics.fillRect(
                locationX,
                locationY,
                TetrisConstant.CELL_WIDTH * width,
                TetrisConstant.CELL_HEIGHT * height);
    }

    /**
     * 画小方块
     */
    protected void drawCell(Graphics graphics, int px, int py, Color color){
        graphics.setColor(color);
        graphics.fillRect(px, py, TetrisConstant.CELL_WIDTH, TetrisConstant.CELL_HEIGHT);
        graphics.setColor(TetrisConstant.COLOR_WHITE);
        graphics.drawRect(px, py, TetrisConstant.CELL_WIDTH - 1, TetrisConstant.CELL_HEIGHT - 1);
    }

    /**
     * 画小网格
     */
    protected void drawGrid(Graphics graphics, int px, int py, Color color){
        graphics.setColor(color);
        graphics.drawRect(
                px,
                py,
                TetrisConstant.CELL_WIDTH,
                TetrisConstant.CELL_HEIGHT);
    }

    /**
     * 初始化数组
     */
    public void clearBody(){
        for(int x = 0; x < body.length; x++){
            for(int y = 0; y < body[x].length; y++){
                body[x][y] = 0;
            }
        }
    }

    /**
     * 将具体的容器样式交给子类去实现.
     */
    public abstract void drawMe(Graphics graphics);
}
