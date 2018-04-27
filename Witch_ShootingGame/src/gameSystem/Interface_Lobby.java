package gameSystem;

import gameScene.BattleScene;
import gameScene.GameScene;
import gameScene.GameScene;
import gameScene.LobbyScene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Paint;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;

import javax.swing.JPanel;

import subClasses.GameObject;
import main.GameManager;

public class Interface_Lobby extends JPanel {

	///////////////////////////////데이터 //////////////////////////////////////////////
	GameScene gc;
	GameObject go;
	
	Interface_Status status;
	
	
	Image img_bar;
	double rbx,rby,rbw,rbh;
	
	double iconSize;
	double iconUnpopY;
	double iconPopY;
	
	Image icon_itemActive;
	Image icon_itemDeactive;
	Image icon_item;
	double iix,iiy,iiw,iih,iiyt;
	
	Image icon_skillActive;
	Image icon_skillDeactive;
	Image icon_skill;
	double isx,isy,isw,ish,isyt;
	
	Image icon_charActive;
	Image icon_charDeactive;
	Image icon_char;
	double icx,icy,icw,ich,icyt;
	
	Image icon_dungeonActive;
	Image icon_dungeonDeactive;
	Image icon_dungeon;
	double idx,idy,idw,idh,idyt;
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////메서드//////////////////////////////////////////////////
	///////////////////////////////construct//////////////////////////////////////////
	public Interface_Lobby(GameScene scene,GameObject gameObject){
		go = gameObject;
		gc = scene;
		init();
		initComponents();
	}
	public void init(){
		
		this.setLayout(null);
		this.setSize(gc.getWidth(),gc.getHeight());	
		this.setVisible(false);
		this.setOpaque(true);	
	}
	public void initComponents(){
		//////////////////////////////////init frames//////////////////////////////
		status = new Interface_Status(gc);
		
		//////////////////////////////////////////////////////////////////////
		
		
		///////////////////////////////////init bar////////////////////////////////
		img_bar = gc.getImgBox().img_bar;
		rbx = 450;
		rby = this.getHeight()-130+100;
		rbw = 800;
		rbh = 5;
		//////////////////////////////////////////////////////////////////////////
		
		///////////////////////////////init buttons///////////////////////////////
		iconSize = 40;
		iconUnpopY = rby - iconSize*1/2;
		iconPopY = rby - iconSize - rbh;
		
		icon_itemActive = gc.getImgBox().icon_itemActive;
		icon_itemDeactive = gc.getImgBox().icon_itemDeactive;
		icon_item = icon_itemActive;
		iiw = iconSize;
		iih = iconSize;
		iix = rbx+rbw*1/13;
		iiy = rby-iih-rbh;
		iiyt = iconUnpopY;
		
		icon_skillActive = gc.getImgBox().icon_skillActive;
		icon_skillDeactive = gc.getImgBox().icon_skillDeactive;
		icon_skill = icon_skillActive;
		isw = iconSize;
		ish = iconSize;
		isx = rbx+rbw*2/13;
		isy = rby-ish-rbh;
		isyt = iconUnpopY;
		
		icon_charActive = gc.getImgBox().icon_charActive;
		icon_charDeactive = gc.getImgBox().icon_charDeactive;
		icon_char = icon_charActive;
		icw = iconSize;
		ich = iconSize;
		icx = rbx+rbw*3/13;
		icy = rby-ich-rbh;
		icyt = iconUnpopY;
		
		icon_dungeonActive = gc.getImgBox().icon_dungeonActive;
		icon_dungeonDeactive = gc.getImgBox().icon_dungeonDeactive;
		icon_dungeon = icon_dungeonActive;
		idw = iconSize;
		idh = iconSize;
		idx = rbx+rbw*12/13;
		idy = rby-ich-rbh;
		idyt = iconUnpopY;
		///////////////////////////////////////////////////////////////////////////
	}
	///////////////////////////////////////////////////////////////////////
	
	///////////////////////////process control//////////////////////////////
	public void activate(){
		this.setLocation(0,0);
		this.setVisible(true);
		gc.add(this);
		
		status.activate();
	}
	public void deactivate(){
		this.setVisible(false);
		gc.remove(this);
		
		status.deactivate();
	}
	///////////////////////////////////////////////////////////////////////////
	
	////////////////////////is, get, set values///////////////////////////////
	public boolean isInRect(int x, int y,Rectangle rect){
		boolean checkTemp = false;
		
		if(x>= rect.x && x<= rect.width +rect.x && y>= rect.y && y<= rect.getMaxY())
			checkTemp =true;
		
		return checkTemp;
	}
	//////////////////////////////////////////////////////////////////////////
	
	//////////////////////paint, process, event///////////////////////////////
	public void paint(Image img){
		Graphics g2d = (Graphics2D)img.getGraphics();
		
	
		//////////////   bar -> icon ///////////////
		g2d.drawImage(img_bar,(int)rbx,(int)rby,(int)rbw,(int)rbh,null);	
		
		g2d.drawImage(icon_item,(int)iix,(int)iiy,(int)iiw,(int)iih,null);
		g2d.drawImage(icon_skill,(int)isx,(int)isy,(int)isw,(int)ish,null);
		g2d.drawImage(icon_char,(int)icx,(int)icy,(int)icw,(int)ich,null);
		g2d.drawImage(icon_dungeon,(int)idx,(int)idy,(int)idw,(int)idh,null);
		//////////////////////////////////////////////////////////////////
		
		////////////////frames////////////////////////////////////////////
		status.paint(img);
		///////////////////////////////////////////////////////////////////////////
	}
	
	public void process(){
		//////////////////////////////process mouse on icon event//////////////////////////////
		PointerInfo ms = MouseInfo.getPointerInfo();
		if(isInRect(ms.getLocation().x,ms.getLocation().y,new Rectangle((int)iix,(int)iconPopY,(int)iiw,(int)iih*3))){
			icon_item = icon_itemActive;
			iiyt = iconPopY;
		}
		else{
			icon_item = icon_itemDeactive;
			iiyt = iconUnpopY;
		}
		
		if(isInRect(ms.getLocation().x,ms.getLocation().y,new Rectangle((int)isx,(int)iconPopY,(int)isw,(int)ish*3))){
			icon_skill = icon_skillActive;
			isyt = iconPopY;
		}
		else{
			icon_skill = icon_skillDeactive;
			isyt = iconUnpopY;
		}
		
		if(isInRect(ms.getLocation().x,ms.getLocation().y,new Rectangle((int)icx,(int)iconPopY,(int)icw,(int)ich*3))){
			icon_char = icon_charActive;
			icyt = iconPopY;
		}
		else{
			icon_char = icon_charDeactive;
			icyt = iconUnpopY;
		}
		
		if(isInRect(ms.getLocation().x,ms.getLocation().y,new Rectangle((int)idx,(int)idy,(int)idw,(int)idh*3))){
			icon_dungeon = icon_dungeonActive;
			idyt = iconPopY;
		}
		else{
			icon_dungeon = icon_dungeonDeactive;
			idyt = iconUnpopY;
		}
		//////////////////////////////////////////////////////////////////////
		
		/////////////////////////process  icon move /////////////////////
		int iconMoveSpeed = 2;
		if(iiy - iiyt >iconMoveSpeed)
			iiy-= iconMoveSpeed;
		else if(iiy - iiyt <-iconMoveSpeed)
			iiy+=iconMoveSpeed;
		
		if(isy - isyt >iconMoveSpeed)
			isy-= iconMoveSpeed;
		else if(isy - isyt <-iconMoveSpeed)
			isy+=iconMoveSpeed;
		
		if(icy - icyt >iconMoveSpeed)
			icy-= iconMoveSpeed;
		else if(icy - icyt <-iconMoveSpeed)
			icy+=iconMoveSpeed;
		
		if(idy - idyt >iconMoveSpeed)
			idy-= iconMoveSpeed;
		else if(idy - idyt <-iconMoveSpeed)
			idy+=iconMoveSpeed;
		////////////////////////////////////////////////////////////////
	}
	
	public void mouseClicked(MouseEvent me) {
		// TODO Auto-generated method stub

		if(isInRect(me.getXOnScreen(),me.getYOnScreen(),new Rectangle((int)iix,(int)iconPopY,(int)iiw,(int)iih*3))){
			
		}
		if(isInRect(me.getXOnScreen(),me.getYOnScreen(),new Rectangle((int)isx,(int)iconPopY,(int)isw,(int)ish*3))){
		
		}
		if(isInRect(me.getXOnScreen(),me.getYOnScreen(),new Rectangle((int)icx,(int)iconPopY,(int)icw,(int)ich*3))){
			if(!status.isVisible())
				status.popUp();
			else
				status.unPop();
		}
		if(isInRect(me.getXOnScreen(),me.getYOnScreen(),new Rectangle((int)idx,(int)idy,(int)idw,(int)idh*3))){
				gc.getGameManager().chaingeScene(new BattleScene(gc.getGameManager()));
		}

	}
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}
	public void mousePressed(MouseEvent me) {
		// TODO Auto-generated method stu
	}
	public void mouseReleased(MouseEvent me) {
		// TODO Auto-generated method stu
	}
	public void keyPressed(KeyEvent ke) {
		if(ke.getKeyCode() == ke.VK_C){
			if(!status.isVisible())
				status.popUp();
			else
				status.unPop();
		}
	}	
	////////////////////////////////////////////////////////////////////
}
