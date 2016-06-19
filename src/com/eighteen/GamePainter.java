package com.eighteen;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 *画图类
 */
public class GamePainter {
	private Judgement jw;
	/**
	 * 预先加载图片到数组里
	 */
	public GamePainter(Judgement judgement){
		this.jw = judgement;
		int num=2;
		for (int i = 0; i < Constant.img100.length; i++ ) {
			try {
				Constant.loc100 ="images/numbers/"+num+".png";
				Constant.img100[i]=ImageIO.read(new File(Constant.loc100));
				Constant.loc80 ="images/numbers80/"+num+".png";
				Constant.img80[i]=ImageIO.read(new File(Constant.loc80));
				num=num*2;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * JFrame的paint方法调用的画图方法
	 * @param g
     */
	public void paint(Graphics g) {
		for(int x = 0; x<Constant.ACCOUNT; x++)
			for(int y = 0; y<Constant.ACCOUNT; y++){
				g.setColor(Color.lightGray);
				g.fillRect(20+x*(Constant.SIZE +5), 60+y*(Constant.SIZE +5), Constant.SIZE, Constant.SIZE);
			}

		for(int x = 0; x<Constant.ACCOUNT; x++)
			for(int y = 0; y<Constant.ACCOUNT; y++)
			{
				if(jw.loc[x][y]!=-1) {
					//如果选中4X4就加载100像素打图片，否在加载80像素的图片
					if (Constant.isImg100) {
						g.drawImage(Constant.img100[jw.loc[x][y]], 20 + y * (Constant.SIZE + 5), 60 + x * (Constant.SIZE + 5), null);
					}else {
						g.drawImage(Constant.img80[jw.loc[x][y]], 20 + y * (Constant.SIZE + 5), 60 + x * (Constant.SIZE + 5), null);
					}
				}
			}
		//显示分数
		g.setFont(new Font("Tahoma", Font.BOLD, 21));
		g.setColor(Color.BLACK);
		g.drawString("分数:",50,40);
		g.drawString(Integer.toString(Constant.grade),110,40);

		g.drawString("游戏步数:",255,40);
		g.drawString(Integer.toString(Constant.count),360,40);


		}
	}



