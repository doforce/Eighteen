package com.eighteen;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;

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
	 * @param judgeWin
	 * @param myJFrame
	 * @param button
     */
	public  void RestartGame(JudgeWin judgeWin,MyJFrame myJFrame,JButton button){
		resetting(judgeWin,myJFrame);
		button.setFocusable(false);
		myJFrame.setFocusable(true);
	}

	/**
	 * 选择游戏模式的事件
	 * @param event
	 * @param judgeWin
	 * @param jComboBox
	 * @param myJFrame
     */
	public  void gameMode(ItemEvent event,JudgeWin judgeWin,JComboBox jComboBox,MyJFrame myJFrame){
		if (event.getStateChange()==ItemEvent.SELECTED){
			if (event.getItem().equals("4X4")){
				Constant.ACCOUNT=4;
				Constant.SIZE=100;
				Constant.isImg100=true;
				changeMode(judgeWin,jComboBox,myJFrame);
			}else {
				Constant.ACCOUNT=5;
				Constant.SIZE=80;
				Constant.isImg100=false;
				changeMode(judgeWin,jComboBox,myJFrame);
			}
		}
	}

	/**
	 * 监听键盘
	 * @param
     */
	public void keyListener(KeyEvent event,JudgeWin judgeWin,MyJFrame frame){
		if(event.getKeyCode()>=36 && event.getKeyCode()<=40){		//判断是否按下方向盘
			judgeWin.change=false;
			judgeWin.move(event.getKeyCode());
			if(judgeWin.over())
			{
				JOptionPane.showMessageDialog(null, "你输了");
			}
			else if(judgeWin.change){
				boolean check=true;
				while(check){
					int x=(int)(Math.random()*Constant.ACCOUNT);
					int y=(int)(Math.random()*Constant.ACCOUNT);
					if(judgeWin.loc[x][y]==-1){
						check=false;
						judgeWin.loc[x][y]=0;
					}
				}
			}
		}
		frame.repaint();
		if(judgeWin.win())
		{
			JOptionPane.showMessageDialog(null, "哇哇！好厉害哦");
		}

	}

	private void resetting(JudgeWin judgeWin,MyJFrame myJFrame){
		judgeWin.init();
		judgeWin.isOver =false;
		judgeWin.grade =0;
		myJFrame.repaint();
	}

	private void changeMode(JudgeWin judgeWin, JComboBox jComboBox, MyJFrame myJFrame){
		resetting(judgeWin,myJFrame);
		jComboBox.setFocusable(false);
		myJFrame.setFocusable(true);
	}
}
