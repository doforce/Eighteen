package com.eighteen;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class GameBoard  {
	private JudgeWin jw;
	/**
	 * 预先加载图片到数组里
	 */
	public GameBoard(JudgeWin judgeWin){
		this.jw =judgeWin;
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
					if (Constant.isImg100) {
						g.drawImage(Constant.img100[jw.loc[x][y]], 20 + y * (Constant.SIZE + 5), 60 + x * (Constant.SIZE + 5), null);
					}else {
						g.drawImage(Constant.img80[jw.loc[x][y]], 20 + y * (Constant.SIZE + 5), 60 + x * (Constant.SIZE + 5), null);
					}
				}
			}

		g.setFont(new Font("Tahoma", Font.BOLD, 21));
		g.setColor(Color.BLACK);
		g.drawString("分数：",40,40);
		g.drawString(Integer.toString(JudgeWin.grade),100,40);

		g.drawString("所用时间：",250,40);

//		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("mm:ss");
//		Date date=new Date();
//		String s=simpleDateFormat.format(date);
//		System.out.println(s);

		}
	}



