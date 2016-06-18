package com.eighteen;

import java.awt.event.KeyEvent;

/**
 * 逻辑类
 */
public class Judgement {
    public int loc[][]=new int[Constant.SIZE][Constant.SIZE];
    public static boolean change;
    public static int grade;
    public static boolean isOver =false;

    public Judgement(){
        init();
    }

    /**
     * 随机位置，随机2或4
     */
    public void randomNum(){
        int i1=(int)(Math.random()*Constant.ACCOUNT);
        int j1=(int)(Math.random()*Constant.ACCOUNT);
        int i2=(int)(Math.random()*Constant.ACCOUNT);
        int j2=(int)(Math.random()*Constant.ACCOUNT);
        //预防两个位置重合，再随机一次
        if (i1==i2 && j1==j2){
            i1=(int)(Math.random()*Constant.ACCOUNT);
        }
        int num1=(int)(1+Math.random()*3);
        if(num1==1){
            num1=0;
        }else{
            num1=1;
        }
        int num2=(int)(1+Math.random()*3);
        if(num2==1){
            num2=0;
        }else{
            num2=1;
        }
        //-1为空，0-11分别代表2-4096
        for(int x = 0; x<Constant.ACCOUNT; x++)
            for(int y = 0; y<Constant.ACCOUNT; y++)
            {
                loc[x][y]=-1;
            }
        loc[i1][j1]=num1;
        loc[i2][j2]=num2;
    }
    public void init(){
        randomNum();
        calGrade();
    }

    public static int[] sort(int[] list,int flag) {
        if(flag==0){
            for(int i = 0; i<Constant.ACCOUNT; i++){
                if(list[i]==-1)continue;
                for(int j = i+1; j<Constant.ACCOUNT; j++)
                    if(list[j]!=-1){
                        if(list[i]==list[j]){
                            list[i]=list[i]+1;
                            list[j]=-1;
                            change=true;
                        }
                        break;
                    }
            }
            int k=0;
            for(int i = 0; i<Constant.ACCOUNT; i++){
                if(list[i]!=-1){
                    list[k]=list[i];
                    if(k<i){
                        list[i]=-1;
                        change=true;
                    }
                    k++;
                }
            }
        }
        else{
            for(int i = Constant.ACCOUNT -1; i>=0; i--){
                if(list[i]==-1)continue;
                for(int j=i-1;j>=0;j--)
                    if(list[j]!=-1){
                        if(list[i]==list[j]){
                            list[i]=list[i]+1;
                            list[j]=-1;
                            change=true;
                        }
                        break;
                    }
            }
            int k=Constant.ACCOUNT-1;
            for(int i = Constant.ACCOUNT -1; i>=0; i--){
                if(list[i]!=-1){
                    list[k]=list[i];
                    if(k>i){
                        list[i]=-1;
                        change=true;
                    }
                    k--;
                }
            }
        }
        return list;
    }

    /**
     * 判断游戏是否结束
     * @return
     */
    public boolean over(){
        for(int h = 0; h<Constant.ACCOUNT; h++)
            for(int l = Constant.ACCOUNT -1; l>=0; l--){
                if(loc[h][l]==-1)
                    return false;
                if(h>0 && loc[h][l]== loc[h-1][l])
                    return false;
                if(l<Constant.ACCOUNT-1 && loc[h][l]== loc[h][l+1])
                    return false;
            }
        return true;
    }

    /**
     * 4X4模式下玩到2048就胜利，5X5模式下玩到4096就胜利
     * @return
     */
    public boolean win(){
        for(int h = 0; h<Constant.ACCOUNT; h++)
            for(int l = Constant.ACCOUNT -1; l>=0; l--){
                if (Constant.isImg100){
                    if(loc[h][l]==10)
                        return true;
                }else {
                    if(loc[h][l]==11)
                        return true;
                }
            }
        return false;
    }

    /**
     * 计算分值
     * @return
     */
    public int calGrade()
    {
        int res=0;
        for(int h = 0; h<Constant.ACCOUNT; h++)
            for(int l = 0; l<Constant.ACCOUNT; l++)
                if(loc[h][l]!=-1)
                    res+=Math.pow(2, loc[h][l])* loc[h][l];
        return res;
    }

    /**
     *把相邻的又数字相同的合并，并且计算分数
     * @param key
     */
    public void move(int key){
        int h,l;
        int tmp[]=new int[Constant.ACCOUNT];
        //扫描每一行
        if(key== KeyEvent.VK_LEFT){
            for(h=0; h<Constant.ACCOUNT; h++)
                loc[h]=sort(loc[h],0);
            grade = calGrade();
        }
        //扫描每一行
        if(key==KeyEvent.VK_RIGHT){
            for(h=0; h<Constant.ACCOUNT; h++)
                loc[h]=sort(loc[h],1);
            grade = calGrade();
        }
        //扫描每一列
        if(key==KeyEvent.VK_UP){
            for(l=0; l<Constant.ACCOUNT; l++){
                for(h=0;h<Constant.ACCOUNT;h++)
                    tmp[h]= loc[h][l];
                tmp=sort(tmp,0);
                for(h=0; h<Constant.ACCOUNT; h++)
                    loc[h][l]=tmp[h];
            }
            grade = calGrade();
        }
        //扫描每一列
        if(key==KeyEvent.VK_DOWN){
            for(l=0; l<Constant.ACCOUNT; l++){
                for(h=0;h<Constant.ACCOUNT;h++)
                    tmp[h]= loc[h][l];
                tmp=sort(tmp,1);
                for(h=0; h<Constant.ACCOUNT; h++)
                    loc[h][l]=tmp[h];
            }
            grade = calGrade();
        }
    }


}
