package com.setsail.tetris.view;

import com.setsail.tetris.entites.Block;
import com.setsail.tetris.entites.LeftContainer;

import javax.swing.*;
import java.awt.*;

public class LeftContainerPanel extends JPanel {

    private Image image;
    private Graphics graphics;

    public void redisplay(Block nextBlock,LeftContainer leftContainer){
        if(image == null){
            image = this.createImage(this.getWidth(),this.getHeight());
        }else {
            graphics = image.getGraphics();
            if(leftContainer != null){
                leftContainer.drawMe(graphics);
            }
            if(nextBlock != null)
                nextBlock.drawNextBlock(graphics);
        }
    }

    public void paint(Graphics graphics){
        if(image != null){
            graphics.drawImage(image,0,0, this);
        }
    }
}
