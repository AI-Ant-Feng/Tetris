package com.setsail.tetris;

import com.setsail.tetris.controller.Controller;
import com.setsail.tetris.listener.FormListener;
import com.setsail.tetris.util.TetrisConstant;
import com.setsail.tetris.view.LeftContainerPanel;
import com.setsail.tetris.view.RightContainerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TetrisMainFrame extends JFrame implements ActionListener, FormListener {

    private LeftContainerPanel leftContainerPanel;
    private RightContainerPanel rightContainerPanel;
    private Controller controller;
    private JButton buttonStart;
    private JButton buttonPause;
    private JButton buttonContinue;
    private JButton buttonEnd;
    private JLabel labelScore;
    private JLabel labelScoreValue;

    public TetrisMainFrame(){
        init();
    }
    private void init(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setLayout(null);
        this.setSize(422,450);
        this.setLocationRelativeTo(null);

        leftContainerPanel = new LeftContainerPanel();
        leftContainerPanel.setLocation(10, 10);
        leftContainerPanel.setSize(
                TetrisConstant.LEFT_CONTAINER_WIDTH * TetrisConstant.CELL_WIDTH + 5,
                TetrisConstant.LEFT_CONTAINER_HEIGHT * TetrisConstant.CELL_HEIGHT + 5);
        rightContainerPanel = new RightContainerPanel();
        rightContainerPanel.setLocation(105, 10);
        rightContainerPanel.setSize(
                TetrisConstant.RIGHT_CONTAINER_WIDTH * TetrisConstant.CELL_WIDTH+5,
                TetrisConstant.RIGHT_CONTAINER_HEIGHT * TetrisConstant.CELL_HEIGHT+5);
        controller = new Controller(leftContainerPanel, rightContainerPanel);
        controller.addListener(this);
        this.getContentPane().add(leftContainerPanel);
        this.getContentPane().add(rightContainerPanel);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        this.getContentPane().add(jPanel);
        jPanel.setBounds(10,110,80,188);
        buttonStart = new JButton("开始游戏");
        buttonStart.setBounds(0,10,80,25);
        buttonStart.setFont(new Font("黑体", Font.PLAIN,12));
        buttonStart.addActionListener(this);
        buttonStart.setVisible(true);
        buttonStart.setEnabled(true);//这个button生效
        buttonStart.setFocusable(false);

        buttonPause = new JButton("暂停游戏");
        buttonPause.setBounds(0,45,80,25);
        buttonPause.setFont(new Font("黑体", Font.PLAIN,12));
        buttonPause.setVisible(true);
        buttonPause.addActionListener(this);
        buttonPause.setEnabled(false);//这个button生效
        buttonPause.setFocusable(false);

        buttonContinue = new JButton("继续游戏");
        buttonContinue.setBounds(0,45,80,25);
        buttonContinue.setFont(new Font("黑体", Font.PLAIN,12));
        buttonContinue.setVisible(false);
        buttonContinue.addActionListener(this);
        buttonContinue.setEnabled(false);//这个button生效
        buttonContinue.setFocusable(false);

        buttonEnd = new JButton("结束游戏");
        buttonEnd.setBounds(0,80,80,25);
        buttonEnd.setFont(new Font("黑体", Font.PLAIN,12));
        buttonEnd.setVisible(true);
        buttonEnd.addActionListener(this);
        buttonEnd.setEnabled(false);//这个button生效
        buttonEnd.setFocusable(false);

        labelScore = new JLabel("分数：");
        labelScore.setBounds(0,115,80,25);
        labelScoreValue = new JLabel("0");
        labelScoreValue.setForeground(Color.red);
        labelScoreValue.setBounds(0,150,80,25);

        jPanel.add(buttonStart);
        jPanel.add(buttonContinue);
        jPanel.add(buttonPause);
        jPanel.add(buttonEnd);
        jPanel.add(labelScore);
        jPanel.add(labelScoreValue);

        this.setVisible(true);
        this.setFocusable(true);
        this.addKeyListener(controller);
    }

    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            TetrisMainFrame frame = new TetrisMainFrame();
            frame.setVisible(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonStart){
            controller.startGame();
            buttonPause.setEnabled(true);
            buttonPause.setVisible(true);
            buttonContinue.setEnabled(true);
            buttonContinue.setVisible(false);
            buttonEnd.setEnabled(true);
        }else if(e.getSource() == buttonPause){
            controller.pauseGame();
            buttonPause.setVisible(false);
            buttonContinue.setVisible(true);
        }else if(e.getSource() == buttonContinue){
            controller.continueGame();
            buttonContinue.setVisible(false);
            buttonPause.setVisible(true);
        }else if(e.getSource() == buttonEnd){
            controller.endGame();
            buttonPause.setEnabled(false);
            buttonPause.setVisible(true);
            buttonContinue.setEnabled(false);
            buttonContinue.setVisible(false);
            buttonEnd.setEnabled(false);
            labelScoreValue.setText("0");
        }
    }

    @Override
    public void addScore(int score) {
        labelScoreValue.setText(Integer.valueOf(labelScoreValue.getText()) + score + "");
    }
}
