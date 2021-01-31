package com.setsail.tetris.entites;

import com.setsail.tetris.util.TetrisConstant;
import java.awt.*;

public class Block {

    private int[][][] pattern;
    private Color color;
    private int direction;

    private int currentBlockLocationX;
    private int currentBlockLocationY;
    private int speed;
    private int width;
    private int height;
    private int nextBlockLocationX;
    private int nextBlockLocationY;

    public Block(int[][][] pattern, int direction, Color color) {
        this.pattern = pattern;
        this.direction = direction;
        this.color = color;
        init();
    }
    /**
     * 初始化图形出现的位置
     */
    private void init(){
        setBlockWidthAndHeight();
        currentBlockLocationX = TetrisConstant.RIGHT_CONTAINER_WIDTH / 2 - 2;
        currentBlockLocationY = 0 - height;
        speed = TetrisConstant.DOWN_SPEED;
        nextBlockLocationX = TetrisConstant.LEFT_CONTAINER_X + (TetrisConstant.LEFT_CONTAINER_WIDTH - width) / 2 * TetrisConstant.CELL_WIDTH;
        nextBlockLocationY = TetrisConstant.LEFT_CONTAINER_Y + (TetrisConstant.LEFT_CONTAINER_HEIGHT - height) / 2 * TetrisConstant.CELL_HEIGHT;
    }

    /**
     * 旋转图形
     */
    public void rotatePattern(){
        direction = (direction + 1) % pattern.length;
        setBlockWidthAndHeight();
    }

    /**
     * 计算图形的宽度和高度，单位：格
     */
    private void setBlockWidthAndHeight(){
        height = 0;
        width = 0;
        int temp = 0;
        for(int i = 0; i < pattern[direction].length; i++){
            for(int j = 0; j < pattern[direction][i].length; j++){
                if(pattern[direction][i][j] == 1){
                    height = i + 1;
                    temp = j + 1;
                    width = width > temp ? width : temp;
                }
            }
        }
    }

    /**
     * 向下移动
     */
    public void moveDown(){
        currentBlockLocationY++;
    }
    /**
     * 向左移动
     */
    public void moveLeft(){
        currentBlockLocationX--;
    }

    /**
     * 向右移动
     */
    public void moveRight(){
        currentBlockLocationX++;
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
     * 画出当前block（在游戏区的block）
     */
    public void drawCurrentBlock(Graphics graphics){
        for(int i = 0; i < pattern[direction].length; i++){
            for(int j = 0; j < pattern[direction][i].length; j++){
                if(pattern[direction][i][j] == 1){
                    drawCell(graphics,
                            TetrisConstant.RIGHT_CONTAINER_X + (currentBlockLocationX + j) * TetrisConstant.CELL_WIDTH,
                            TetrisConstant.RIGHT_CONTAINER_Y + (currentBlockLocationY + i) * TetrisConstant.CELL_HEIGHT,
                            color);
                }
            }
        }
    }
    /**
     * 画出下一个block（在预告区的block）
     */
    public void drawNextBlock(Graphics graphics){
        for(int i = 0; i < pattern[direction].length; i++){
            for(int j = 0; j < pattern[direction][i].length; j++){
                if(pattern[direction][i][j] == 1){
                    drawCell(graphics,
                            nextBlockLocationX + j * TetrisConstant.CELL_WIDTH,
                            nextBlockLocationY + i * TetrisConstant.CELL_HEIGHT,
                            color);
                }
            }
        }
    }

    public int[][][] getPattern() {
        return pattern;
    }

    public void setPattern(int[][][] pattern) {
        this.pattern = pattern;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getCurrentBlockLocationX() {
        return currentBlockLocationX;
    }

    public void setCurrentBlockLocationX(int currentBlockLocationX) {
        this.currentBlockLocationX = currentBlockLocationX;
    }

    public int getCurrentBlockLocationY() {
        return currentBlockLocationY;
    }

    public void setCurrentBlockLocationY(int currentBlockLocationY) {
        this.currentBlockLocationY = currentBlockLocationY;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getNextBlockLocationX() {
        return nextBlockLocationX;
    }

    public void setNextBlockLocationX(int nextBlockLocationX) {
        this.nextBlockLocationX = nextBlockLocationX;
    }

    public int getNextBlockLocationY() {
        return nextBlockLocationY;
    }

    public void setNextBlockLocationY(int nextBlockLocationY) {
        this.nextBlockLocationY = nextBlockLocationY;
    }
}
