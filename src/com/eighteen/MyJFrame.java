package com.eighteen;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import java.awt.event.*;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Graphics;

public class MyJFrame extends JFrame  {

	private JPanel panelOne;
    private GameBoard gameBoard;
    private JComboBox cbGameMode;
    //获取屏幕宽和高
    private int width=Toolkit.getDefaultToolkit().getScreenSize().width;
    private int height=Toolkit.getDefaultToolkit().getScreenSize().height;

    private JButton btnRestartGame;
	public  MyJFrame mjf;
	private JudgeWin jw;

	

    public MyJFrame() {
		jw =new JudgeWin();
    	gameBoard=new GameBoard(jw);
    	mjf =this;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constant.width,Constant.height);
        setLocation((width-Constant.width)/2,(height-Constant.height)/2);
        setResizable(false);
        initView();
		//获取焦点
		this.setFocusable(true);

		addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				EventListener.getInstance().keyListener(e,jw,mjf);
			}
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
		});
    }
    
    @Override
    public void paint(Graphics g) {
    	super.paint(g);
			gameBoard.paint(g);
    }

    /**
     * 初始化控件
     */
    public void initView(){
        panelOne = new JPanel();
        panelOne.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(panelOne);
        panelOne.setLayout(null);

		//下拉列表
		cbGameMode = new JComboBox();
		cbGameMode.addItemListener(e -> EventListener.getInstance().gameMode(e,jw,cbGameMode,mjf));
		cbGameMode.addItem("4X4");
		cbGameMode.addItem("5X5");
		cbGameMode.setBounds(450, 240, 95, 30);
		panelOne.add(cbGameMode);

        btnRestartGame = new JButton("重新开始");
        btnRestartGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				EventListener.getInstance().RestartGame(jw,mjf,btnRestartGame);
			}
		});
        btnRestartGame.setBounds(450, 75, 95, 30);
        panelOne.add(btnRestartGame);
    }
}