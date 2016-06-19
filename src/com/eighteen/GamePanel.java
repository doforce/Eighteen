package com.eighteen;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;

/**
 * 窗体类
 */
public class GamePanel extends JFrame  {

	private JPanel panelOne;
    private GamePainter gamePainter;
    private JComboBox cbGameMode;

    private JButton btnRestartGame;
	public GamePanel gp;
	private Judgement jw;

    public GamePanel() {
		jw =new Judgement();
    	gamePainter =new GamePainter(jw);
    	gp =this;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(Constant.width,Constant.height);
        setLocation((Constant.w-Constant.width)/2,(Constant.h-Constant.height)/2);
        setResizable(false);
        initView();
		//获取焦点
		this.setFocusable(true);

		/**
		 * 监听键盘事件
		 */
		addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				EventListener.getInstance().keyListener(e,jw, gp,btnRestartGame);
			}
			@Override
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
		});
    }

	/**
	 * 绘制2048游戏
	 * @param g
     */
    @Override
    public void paint(Graphics g) {
    	super.paint(g);
			gamePainter.paint(g);
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
		//监听下拉列表的Item值改变
		cbGameMode.addItemListener(e -> EventListener.getInstance().gameMode(e,jw,cbGameMode, gp));
		cbGameMode.addItem("4X4");
		cbGameMode.addItem("5X5");
		cbGameMode.setBounds(445, 360, 95, 30);
		panelOne.add(cbGameMode);

        btnRestartGame = new JButton("重新开始");
        btnRestartGame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				EventListener.getInstance().restartGame(jw, gp,btnRestartGame);
			}
		});
        btnRestartGame.setBounds(445, 145, 95, 30);
        panelOne.add(btnRestartGame);

    }
}