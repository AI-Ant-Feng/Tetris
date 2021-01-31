package com.setsail.tetris.controller;

import com.setsail.tetris.entites.Block;
import com.setsail.tetris.entites.BlockFactory;
import com.setsail.tetris.entites.LeftContainer;
import com.setsail.tetris.entites.RightContainer;
import com.setsail.tetris.listener.ContainerListener;
import com.setsail.tetris.listener.FormListener;
import com.setsail.tetris.util.BlockAction;
import com.setsail.tetris.util.KeyBoardConstant;
import com.setsail.tetris.util.TetrisConstant;
import com.setsail.tetris.view.LeftContainerPanel;
import com.setsail.tetris.view.RightContainerPanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter implements KeyBoardConstant, ContainerListener, Runnable{

    public static final int INIT = 1;
    public static final int PLAYING = 2;
    public static final int PAUSE = 3;
    public static final int SUCCESS = 5;
    public static final int FAIL = 6;
    private Block currentBlock;
    private Block nextBlock;
    private BlockFactory blockFactory;
    private RightContainer rightContainer;
    private LeftContainer leftContainer;
    public static int gameState;
    private LeftContainerPanel leftContainerPanel;
    private RightContainerPanel rightContainerPanel;
    private FormListener formListener;
    private int flag = 0;

    public Controller(LeftContainerPanel leftContainerPanel, RightContainerPanel rightContainerPanel) {
        this.leftContainerPanel = leftContainerPanel;
        this.rightContainerPanel = rightContainerPanel;
        init();
    }

    public void init(){
        blockFactory = new BlockFactory();
        leftContainer = new LeftContainer();
        rightContainer = new RightContainer();
        rightContainer.setListener(this);
        setState(INIT);
        new Thread(this).start();
    }

    public void startGame() {
        clear();
        currentBlock = blockFactory.createBlock();
        nextBlock = blockFactory.createBlock();
        leftContainer.init();
        rightContainer.init();
        setState(PLAYING);
    }

    public void pauseGame() {
        setState(PAUSE);
    }

    public void continueGame() {
        setState(PLAYING);
    }

    public void endGame() {
        clear();
        setState(INIT);
    }

    /**
     * 清除图形block
     */
    private void clear(){
        currentBlock = null;
        nextBlock = null;
    }

    /** 把下一个方块给当前方块，然后重新产生下一个方块 */
    private void proNewBlock(){
        currentBlock = nextBlock;
        nextBlock = blockFactory.createBlock();
    }

    /**
     * 刷新当前游戏区页面
     */
    private void flush(){
        switch (gameState){
            case INIT:
                rightContainerPanel.redisplay(currentBlock, rightContainer);
                rightContainerPanel.repaint();
                leftContainerPanel.redisplay(nextBlock, leftContainer);
                leftContainerPanel.repaint();
                break;
            case PLAYING:
                if (flag == TetrisConstant.DOWN_SPEED - 1)
                {
                    if (rightContainer.isMoveAble(currentBlock, BlockAction.DOWN))
                    {
                        currentBlock.moveDown();
                    }
                    else
                    {
                        rightContainer.setBarrier(currentBlock);
                        if (!rightContainer.isFull())
                        {
                            // 产生新图像
                            proNewBlock();
                        }
                    }
                }
                flag = (++flag) % TetrisConstant.DOWN_SPEED;
                rightContainerPanel.redisplay(currentBlock, rightContainer);
                rightContainerPanel.repaint();
                leftContainerPanel.redisplay(nextBlock, leftContainer);
                leftContainerPanel.repaint();
                break;
            case PAUSE:
                rightContainerPanel.redisplay(currentBlock, rightContainer);
                rightContainerPanel.repaint();
                leftContainerPanel.redisplay(nextBlock, leftContainer);
                leftContainerPanel.repaint();
                break;
            case FAIL:
                rightContainerPanel.redisplay(null, rightContainer);
                rightContainerPanel.repaint();
                leftContainerPanel.redisplay(nextBlock, leftContainer);
                leftContainerPanel.repaint();
                break;
        }
    }


    /** 设置游戏状态 */
    public static void setState(int state) {
        gameState = state;
    }

    @Override
    public void containerIsFull() {
        setState(FAIL);
    }

    @Override
    public void removeFullLine(int score) {
        formListener.addScore(score);
    }

    @Override
    public void run() {
        while (true){
            flush();
            try{
                Thread.sleep(TetrisConstant.SLEEP_TIME);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e){
        if(gameState == PLAYING){
            switch (e.getKeyCode()){
                case KeyEvent.VK_UP:
                    if(rightContainer.isMoveAble(currentBlock, BlockAction.ROTATE)){
                        currentBlock.rotatePattern();
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(rightContainer.isMoveAble(currentBlock, BlockAction.DOWN)){
                        currentBlock.moveDown();
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if(rightContainer.isMoveAble(currentBlock, BlockAction.LEFT)){
                        currentBlock.moveLeft();
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(rightContainer.isMoveAble(currentBlock, BlockAction.RIGHT)){
                        currentBlock.moveRight();
                    }
                    break;
            }
        }
    }

    public void addListener(FormListener formListener) {
        this.formListener = formListener;
    }
}
