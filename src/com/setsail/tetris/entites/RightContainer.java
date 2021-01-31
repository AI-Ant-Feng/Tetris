package com.setsail.tetris.entites;

import com.setsail.tetris.listener.ContainerListener;
import com.setsail.tetris.util.BlockAction;
import com.setsail.tetris.util.TetrisConstant;

import java.awt.*;

public class RightContainer extends ContainerFactory {

    private boolean full;
    private int score;
    private int fullFlag = 0;// 满行标记
    private ContainerListener listener;
    private Color barrierColor;

    public RightContainer() {
        init();
    }

    public void init(){
        body =  new int[TetrisConstant.RIGHT_CONTAINER_HEIGHT][TetrisConstant.RIGHT_CONTAINER_WIDTH];
        super.clearBody();
        full = false;
        backGroundColor = TetrisConstant.COLOR_LIGHTGRAY;
        gridColor = TetrisConstant.COLOR_BLACK;
        barrierColor = TetrisConstant.COLOR_DIMGRAY;
        locationX = TetrisConstant.RIGHT_CONTAINER_X;
        locationY = TetrisConstant.RIGHT_CONTAINER_Y;
        width = TetrisConstant.RIGHT_CONTAINER_WIDTH;
        height = TetrisConstant.RIGHT_CONTAINER_HEIGHT;
    }

    /**
     * 设置障碍物
     */
    public void setBarrier(Block block){
        int[][] tmp = block.getPattern()[block.getDirection()];
        for(int i = tmp.length - 1; i >= 0; i--){
            for(int j = 0; j < tmp[i].length; j++){
                if(tmp[i][j] == 1){
                    if(i + block.getCurrentBlockLocationY() < 0){
                        full = true;
                        listener.containerIsFull();
                        return;
                    }else{
                        this.body[i + block.getCurrentBlockLocationY()][j + block.getCurrentBlockLocationX()] = tmp[i][j];
                    }
                }
            }
        }
        deleteFullLine();
    }

    /** 检测能否移动 */
    public boolean isMoveAble(Block block, int action){
        int tmpCurrentBlockLocationX = block.getCurrentBlockLocationX();
        int tmpCurrentBlockLocationY = block.getCurrentBlockLocationY();
        int tmpDirection = block.getDirection();
        int[][][] blockPattern = block.getPattern();

        switch (action){
            case BlockAction.DOWN :
                tmpCurrentBlockLocationY++;
                break;
            case BlockAction.LEFT:
                tmpCurrentBlockLocationX--;
                break;
            case BlockAction.RIGHT:
                tmpCurrentBlockLocationX++;
                break;
            case BlockAction.ROTATE:
                tmpDirection = (tmpDirection + 1) % blockPattern.length;
                break;
        }

        for(int i = 0; i < blockPattern[tmpDirection].length; i++){
            for(int j = 0; j < blockPattern[tmpDirection][i].length; j++){
                if(blockPattern[tmpDirection][i][j] == 1){
                    if( tmpCurrentBlockLocationX + j < 0 ||
                        tmpCurrentBlockLocationX + j > TetrisConstant.RIGHT_CONTAINER_WIDTH - 1 ||
                        tmpCurrentBlockLocationY + i > TetrisConstant.RIGHT_CONTAINER_HEIGHT - 1){
                        return false;
                    }else {
                        if(tmpCurrentBlockLocationY + i >= 0){
                            if(this.body[tmpCurrentBlockLocationY + i][tmpCurrentBlockLocationX + j] == 1){
                                return false;
                            }
                        }
                    }

                }
            }
        }
      return true;
    }

    /**
     * 扫描并删除满行<BR>
     * 将调用 deleteLine(int) 方法删除满行
     */
    public void deleteFullLine(){
        score = 0;
        /**
         * 这次一共消了几行
         */
        for(int i = body.length - 1; i >=0; i--) {
            boolean isFull = true;

            for (int j = 0; j < body[i].length - 1; j++) {
                if (this.body[i][j] == 0) {
                    isFull = false;
                }
            }

            if (isFull) {
                if (fullFlag == 0) {
                    fullFlag = 7;
                }
                deleteLine(i++);
                score = score + 100;
            }
        }

        if(fullFlag > 0){
            listener.removeFullLine(score);
        }
    }

    /**
     * 删除指定的行(这一行上面所有的方块整体下移一行)
     */
    private void deleteLine(int line) {
        for(int i = 0; i < this.body[line].length; i++){
            this.body[line][i] = 0;
        }
        for(int i = line; i >= 0; i--){
            for(int j = 0; j < body[i].length; j++){
                if(i == 0){
                    body[i][j] = 0;
                }else {
                    body[i][j] = body[i - 1][j];
                }
            }
        }
    }

    @Override
    public void drawMe(Graphics graphics) {
        drawBackgroundColor(graphics);
        for(int x = 0; x < body.length; x++){
            for(int y = 0; y < body[x].length; y++){
                graphics.setColor(gridColor);
                drawGrid(graphics,locationX + y * TetrisConstant.CELL_WIDTH, locationY + x * TetrisConstant.CELL_HEIGHT, gridColor);
                if(body[x][y] == 1){
                    graphics.setColor(barrierColor);
                    drawCell(graphics, locationX + y * TetrisConstant.CELL_WIDTH, locationY + x * TetrisConstant.CELL_HEIGHT, barrierColor);
                }
            }
        }
        if(fullFlag > 0){
            graphics.setColor(TetrisConstant.COLOR_RED);
            String string = "恭喜您得分！ " + "+" + score;
            graphics.setFont(new Font("黑体",Font.BOLD, 15));
            FontMetrics fontMetrics = graphics.getFontMetrics();
            graphics.drawString(
                    string,
                    TetrisConstant.RIGHT_CONTAINER_X + TetrisConstant.RIGHT_CONTAINER_WIDTH / 2 * TetrisConstant.CELL_WIDTH - fontMetrics.stringWidth(string) / 2,
                    TetrisConstant.RIGHT_CONTAINER_Y + TetrisConstant.RIGHT_CONTAINER_HEIGHT / 2 * TetrisConstant.CELL_HEIGHT + fullFlag * 3 + locationY);
            fullFlag--;
        }
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getFullFlag() {
        return fullFlag;
    }

    public void setFullFlag(int fullFlag) {
        this.fullFlag = fullFlag;
    }

    public ContainerListener getListener() {
        return listener;
    }

    public void setListener(ContainerListener listener) {
        this.listener = listener;
    }

    public Color getBarrierColor() {
        return barrierColor;
    }

    public void setBarrierColor(Color barrierColor) {
        this.barrierColor = barrierColor;
    }
}
