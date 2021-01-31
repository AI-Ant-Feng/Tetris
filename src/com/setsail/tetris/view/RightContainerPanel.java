package com.setsail.tetris.view;

import com.setsail.tetris.controller.Controller;
import com.setsail.tetris.entites.Block;
import com.setsail.tetris.entites.RightContainer;

import javax.swing.*;
import java.awt.*;

public class RightContainerPanel extends JPanel {

    private Image image;
    private Graphics graphics;
    int flag1 = 5;
    int flag2 = 0;//0表示向右，1表示向左
    int flag3=0;

    public void redisplay(Block block, RightContainer rightContainer){
        if(image == null){
            image = this.createImage(this.getWidth(), this.getHeight());
        }else {
            graphics = image.getGraphics();

            if(rightContainer != null){
                rightContainer.drawMe(graphics);
            }
            if(block != null){
                block.drawCurrentBlock(graphics);
            }
            if(Controller.gameState == Controller.INIT){
                if(flag3 == 3){
                    if(flag2 == 0){
                        flag1++;
                        if(flag1 == 10){
                            flag2 =1;
                        }
                    }else if(flag2 == 1){
                        flag1--;
                        if(flag1 == 5){
                            flag2 = 0;
                        }
                    }
                }
                flag3 = ++flag3 % 4;
                graphics.setColor(Color.red);
                graphics.setFont(new Font("黑体",Font.PLAIN,15));
                graphics.drawString("欢迎来到俄罗斯方块世界", flag1 * 6 + 20, 100);
            }
        }
    }

    public void paint(Graphics graphics){
        if(image != null){
            graphics.drawImage(image, 0, 0, this);
        }
    }

}
