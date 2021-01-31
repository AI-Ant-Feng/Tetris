package com.setsail.tetris.entites;

import com.setsail.tetris.util.TetrisConstant;

import java.awt.*;

public class LeftContainer extends ContainerFactory {

    public LeftContainer() {
        init();
    }

    public void init(){
        body = new int[TetrisConstant.LEFT_CONTAINER_HEIGHT][TetrisConstant.LEFT_CONTAINER_WIDTH];
        super.clearBody();
        backGroundColor = TetrisConstant.COLOR_LIGHTGRAY;
        gridColor = TetrisConstant.COLOR_BLACK;
        locationX = TetrisConstant.LEFT_CONTAINER_X;
        locationY = TetrisConstant.LEFT_CONTAINER_Y;
        width = TetrisConstant.LEFT_CONTAINER_WIDTH;
        height = TetrisConstant.LEFT_CONTAINER_HEIGHT;
    }

    @Override
    public void drawMe(Graphics graphics) {
        drawBackgroundColor(graphics);
        for(int x = 0; x < body.length; x++){
            for(int y = 0; y < body[x].length; y++){
                graphics.setColor(gridColor);
                drawGrid(graphics,
                        locationX + y * TetrisConstant.CELL_WIDTH,
                        locationY + x * TetrisConstant.CELL_HEIGHT,
                        gridColor);
            }
        }
    }
}
