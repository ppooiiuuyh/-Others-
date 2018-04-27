package gameSystem;

import gameScene.GameScene;
import gameScene.LobbyScene;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import main.GameManager;

public class Interface_Status extends JPanel{
	
	////////////////////////데이터//////////////////////////////////////
	GameScene gc;
	
	///////////////////process flags/////////////////////////////////
	public boolean IS_PROCESSSTOP;
	/////////////////////////////////////////////////////
	
	/////////////////////////////////  menu 컴포넌트 ////////////////////////////////////////////
	Image statusFrame; // inventory frame
	double sfx,sfy,sfw,sfh;
	////////////////////////////////////////////////////////////////////////////////
	

	////////////////////////////////////////////////////////////////////

	///////////////////////////////메서드///////////////////////////////////////
	////////////////////////// construct///////////////////////////////////////
	public Interface_Status(GameScene gameScene)
	{
		gc = gameScene;
		init();
		initComponents();
	}
	
	public void init()
	{
		this.IS_PROCESSSTOP = true;
		this.setLayout(null);
		this.setOpaque(false);
		this.setSize(gc.getWidth(),gc.getHeight());
		this.setLocation(gc.getWidth()/2-this.getWidth()/2,gc.getHeight()/2-this.getHeight()/2);
		this.setVisible(false);
	}
	
	public void initComponents(){
		////////////////////init frame//////////////////////////////////////////////
		statusFrame = gc.getImgBox().statusFrame;
		sfw = 300;
		sfh = 500;
		sfx = this.getWidth() - sfw- 50;
		sfy = this.getHeight()*1/8;
		////////////////////////////////////////////////////////////////////////////
		
		///////////////////init buttons///////////////////////////////
		
		////////////////////////////////////////////////////////////////////////////
		
	}
	///////////////////////////////////////////////////////////////////////////////////////////
	
	
	///////////////////////////////////process control///////////////////////////
	public void activate(){
		
		IS_PROCESSSTOP = true;
		this.setVisible(false);
	}
	
	public void deactivate(){

		this.setVisible(false);
	}

	public void popUp(){
		this.IS_PROCESSSTOP = false;
		this.setVisible(true);
	}
	
	public void unPop(){
		this.IS_PROCESSSTOP = true;
		this.setVisible(false);
	
	}
	////////////////////////////////////////////////////////////////////////////
	
	
	
	//////////////////is,get, set values///////////////////////////////////////////	
	public boolean isInRect(int x, int y,Rectangle rect){
		boolean checkTemp = false;
		
		if(x>= rect.x && x<= rect.width +rect.x && y>= rect.y && y<= rect.getMaxY())
			checkTemp =true;
		
		return checkTemp;
	}
	
	//////////////////////////////////////////////////////////////////////////
	
	/////////////////////paint , process,event //////////////////////////////////////
	public void paint(Image img){
		if(isVisible()){
			Graphics2D g2d =(Graphics2D)img.getGraphics();
			g2d.drawImage(statusFrame,(int)sfx,(int)sfy,(int)sfw,(int)sfh,this);	
		}
	}

	public void process(){
		if(!IS_PROCESSSTOP){
		//////////////////////button process/////////////////////////////////////////////
		PointerInfo ms = MouseInfo.getPointerInfo();
		
		///////////////////////////////////////////////////////////////////////////////////
		}
	}
	
	
	public void mouseClicked(MouseEvent me) {
		// TODO Auto-generated method stub
		/*//////////////////////button cliked////////////////////////////////////////////////////////
		if(!this.IS_PROCESSSTOP){
			if(isInRect(me.getXOnScreen(),me.getYOnScreen(),new Rectangle(rtgx,rtgy,rtgw,rtgh))){
				unPop();
				gc.setPhase(gc.SCENEPHASE);
			}
			if(isInRect(me.getXOnScreen(),me.getYOnScreen(),new Rectangle(rtlx,rtly,rtlw,rtlh))){
				unPop();
			}
			if(isInRect(me.getXOnScreen(),me.getYOnScreen(),new Rectangle(epx,epy,epw,eph))){
				unPop();
			}
		}
		*///////////////////////////////////////////////////////////////////////////////
	}
	////////////////////////////////////////////////////////////////////
}
