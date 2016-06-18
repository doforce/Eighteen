package com.eighteen;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;

/**
 * 事件监听类
 */
public class EventListener {

	/**
	 * 用单例模式创建EventListener的唯一对象
	 */
	private static EventListener eventListener=null;
	private EventListener(){}
	public static EventListener getInstance(){
		if(eventListener==null){
			eventListener=new EventListener();
		}
		return eventListener;
	}

	/**
	 * 重新开始的事件
	 * @param judgement
	 * @param gamePanel
	 * @param button
     */
	public  void RestartGame(Judgement judgement, GamePanel gamePanel, JButton button){
		resetting(judgement, gamePanel);
		button.setFocusable(false);
		gamePanel.setFocusable(true);
	}

	/**
	 * 选择游戏模式的事件
	 * @param event
	 * @param judgement
	 * @param jComboBox
	 * @param gamePanel
     */
	public  void gameMode(ItemEvent event, Judgement judgement, JComboBox jComboBox, GamePanel gamePanel){
		if (event.getStateChange()==ItemEvent.SELECTED){
			if (event.getItem().equals("4X4")){
				Constant.ACCOUNT=4;
				Constant.SIZE=100;
				Constant.isImg100=true;
				changeMode(judgement,jComboBox, gamePanel);
			}else {
				Constant.ACCOUNT=5;
				Constant.SIZE=80;
				Constant.isImg100=false;
				changeMode(judgement,jComboBox, gamePanel);
			}
		}
	}

	/**
	 * 监听键盘
	 * @param event
	 * @param judgement
	 * @param frame
     */
	public void keyListener(KeyEvent event, Judgement judgement, GamePanel frame){
		if(event.getKeyCode()>=36 && event.getKeyCode()<=40){		//判断是否按下方向盘
			judgement.change=false;
			judgement.move(event.getKeyCode());
			if(judgement.over())
			{
				JOptionPane.showMessageDialog(null, "你输了!");
			}
			else if(judgement.change){
				boolean check=true;
				while(check){
					int x=(int)(Math.random()*Constant.ACCOUNT);
					int y=(int)(Math.random()*Constant.ACCOUNT);
					if(judgement.loc[x][y]==-1){
						check=false;
						judgement.loc[x][y]=0;
					}
				}
			}
		}
		frame.repaint();
		if(judgement.win())
		{
			JOptionPane.showMessageDialog(null, "好厉害哦,那么难都赢了！");
		}

	}

	/**
	 * 重置方法
	 * @param judgement
	 * @param gamePanel
     */
	private void resetting(Judgement judgement, GamePanel gamePanel){
		judgement.init();
		judgement.isOver =false;
		judgement.grade =0;
		gamePanel.repaint();
	}

	private void changeMode(Judgement judgement, JComboBox jComboBox, GamePanel gamePanel){
		resetting(judgement, gamePanel);
		jComboBox.setFocusable(false);
		gamePanel.setFocusable(true);
	}
}
