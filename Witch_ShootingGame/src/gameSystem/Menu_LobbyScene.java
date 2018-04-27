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

public class Menu_LobbyScene extends JPanel{
	
	////////////////////////데이터//////////////////////////////////////
	GameScene gc;
	GameManager gm;
		
	///////////////////process flags/////////////////////////////////
	public boolean IS_PROCESSSTOP;
	/////////////////////////////////////////////////////
	
	/////////////////////////////////  menu 컴포넌트 ////////////////////////////////////////////
	Image returnToGameDeactive;
	Image returnToGameActive;
	Image returnToGame;
	int rtgx,rtgy;
	int rtgw,rtgh;
	
	Image returnToTitleDeactive;
	Image returnToTitleActive;
	Image returnToTitle;
	int rtlx,rtly;
	int rtlw,rtlh;

	Image exitProgramDeactive;
	Image exitProgramActive;
	Image exitProgram = exitProgramDeactive;
	int epx,epy;
	int epw,eph;
	////////////////////////////////////////////////////////////////////////////////
	
	
	
	//////////////////글레스 //////////////////////////////////
	Image glass;
	boolean IS_GLASSDRAWED;
	int glassThickness;
	public int gdc;  //glass drawed count
	///////////////////////////////////////////////////////
	
	////////////////////////////////////////////////////////////////////

	///////////////////////////////메서드///////////////////////////////////////
	////////////////////////// construct///////////////////////////////////////
	public Menu_LobbyScene(GameScene gameScene, GameManager gameManager)
	{
		gc = gameScene;
		gm = gameManager;
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
		/////////////////////init glass/////////////////////////////////
		glass = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("redglass3per.png"));
		IS_GLASSDRAWED = false;
		glassThickness =3;
		gdc =0;
		/////////////////////////////////////////////////////////
		
		///////////////////init buttons///////////////////////////////
		returnToGameDeactive = gc.getImgBox().returnToGameDeactive;
		returnToGameActive =gc.getImgBox().returnToLobbyActive;
		returnToGame = returnToGameDeactive;
		rtgw = 400;
		rtgh = 70;
		rtgx = this.getWidth()/2 - rtgw/2;
		rtgy = this.getHeight()*1/4 - rtgh/2;
		
		
		returnToTitleDeactive = gc.getImgBox().returnToTitleDeactive;
		returnToTitleActive =gc.getImgBox().returnToTitleActive;
		returnToTitle = returnToTitleDeactive;
		rtlw = 400;
		rtlh = 70;
		rtlx = this.getWidth()/2 - rtlw/2;
		rtly = this.getHeight()*2/4 - rtlh/2;
		
		exitProgramDeactive = gc.getImgBox().exitProgramDeactive;
		exitProgramActive =gc.getImgBox().exitProgramActive;
		exitProgram = exitProgramDeactive;
		epw = 400;
		eph = 70;
		epx = this.getWidth()/2 - epw/2;
		epy = this.getHeight()*3/4 - eph/2;
		////////////////////////////////////////////////////////////////////////////
	}
	///////////////////////////////////////////////////////////////////////////////////////////
	
	
	///////////////////////////////////process control///////////////////////////
	public void activate(){
		
		this.gc.add(this);
		IS_PROCESSSTOP = true;
		this.setVisible(true);
	}
	
	public void deactivate(){

		this.gc.remove(this);
		this.setVisible(false);
		IS_GLASSDRAWED = false;
	}

	public void popUp(){
		this.IS_PROCESSSTOP = false;
		this.setVisible(true);
		IS_GLASSDRAWED = false;
		gdc=0;

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
	
	public boolean isGlassDrawed(){
		return IS_GLASSDRAWED;
	}
	
	public int getGlassThisckness()
	{
		return glassThickness;
	}
	
	
	public void setGlassDrawed(boolean b){
		IS_GLASSDRAWED = b;
	}
	
	//////////////////////////////////////////////////////////////////////////
	
	/////////////////////paint , process,event //////////////////////////////////////
	public void paint(Image img){
		Graphics2D g2d =(Graphics2D)img.getGraphics();
		g2d.drawImage(returnToGame,rtgx,rtgy,rtgw,rtgh,this);
		g2d.drawImage(returnToTitle,rtlx,rtly,rtlw,rtlh,this);
		g2d.drawImage(exitProgram,epx,epy,epw,eph,this);
			
	}
	public void paintGlass(Image img){
		Graphics2D g2d =(Graphics2D)img.getGraphics();
		g2d.drawImage(glass, this.getLocation().x, this.getLocation().y, this.getWidth(),this.getHeight() ,this);	
		IS_GLASSDRAWED = true;
		gdc++;
	}
	
	public void process(){
		//////////////////////button process/////////////////////////////////////////////
		PointerInfo ms = MouseInfo.getPointerInfo();
		if(isInRect(ms.getLocation().x,ms.getLocation().y,new Rectangle(rtgx,rtgy,rtgw,rtgh)))
			returnToGame = returnToGameActive;
		else
			returnToGame = returnToGameDeactive;
		
		
		if(isInRect(ms.getLocation().x,ms.getLocation().y,new Rectangle(rtlx,rtly,rtlw,rtlh)))
			returnToTitle = returnToTitleActive;
		else
			returnToTitle = returnToTitleDeactive;
		
		
		if(isInRect(ms.getLocation().x,ms.getLocation().y,new Rectangle(epx,epy,epw,eph)))
			exitProgram = exitProgramActive;
		else
			exitProgram = exitProgramDeactive;
		///////////////////////////////////////////////////////////////////////////////////
	}
	
	
	public void mouseClicked(MouseEvent me) {
		// TODO Auto-generated method stub
		if(!this.IS_PROCESSSTOP){
			if(isInRect(me.getXOnScreen(),me.getYOnScreen(),new Rectangle(rtgx,rtgy,rtgw,rtgh))){
				unPop();
				gc.setPhase(gc.SCENEPHASE);
			}
			if(isInRect(me.getXOnScreen(),me.getYOnScreen(),new Rectangle(rtlx,rtly,rtlw,rtlh))){
				unPop();
				gm.chaingeScene(new LobbyScene(gm));
			}
			if(isInRect(me.getXOnScreen(),me.getYOnScreen(),new Rectangle(epx,epy,epw,eph))){
				unPop();
				gm.exitProgram();
			}
		}
	}
	////////////////////////////////////////////////////////////////////
	
	
	
	
}
