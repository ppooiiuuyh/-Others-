package gameSystem;

import gameScene.GameScene;
import gameScene.GameScene;
import gameScene.LobbyScene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;

import javax.swing.JPanel;

import subClasses.GameObject;
import main.GameManager;

public class Interface_Hp extends JPanel {

	///////////////////////////////데이터 //////////////////////////////////////////////
	GameScene gc;
	GameObject go;
	
	
	Image img_hp;
	Image img_hpglass;
	
	
	int isSkill1Createable;
	int isSkill2Createable;
	int isSkill3Createable;
	int isSkill4Createable;
	int isSkill5Createable;
	int isSkill6Createable;
	
	/////////////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////메서드//////////////////////////////////////////////////
	///////////////////////////////construct//////////////////////////////////////////
	public Interface_Hp(GameScene scene,GameObject gameObject){
		go = gameObject;
		gc = scene;
		init();
		initImg();
	}
	public void init(){
		isSkill1Createable =0;
		isSkill2Createable =0;
		isSkill3Createable =0;
		isSkill4Createable =0;
		isSkill5Createable =0;
		isSkill6Createable =0;
		
		this.setLayout(null);
		this.setSize(gc.getWidth(),gc.getHeight());	
		this.setVisible(false);
		this.setOpaque(true);	
	}
	public void initImg(){
		//img_hp = Toolkit.getDefaultToolkit().getImage("interface\\interface_hp.png");
		img_hp = gc.getImgBox().img_hp;
		img_hpglass =gc.getImgBox().img_hpglass;
	}
	///////////////////////////////////////////////////////////////////////
	
	///////////////////////////process control//////////////////////////////
	public void activate(){
		this.setLocation(0,0);
		this.setVisible(true);
		gc.add(this);
	}
	public void deactivate(){
		this.setVisible(false);
		gc.remove(this);
	}
	///////////////////////////////////////////////////////////////////////////
	
	////////////////////////is, get, set values///////////////////////////////
	public void coolTimeUpdate(int s1,int s2, int s3, int s4, int s5, int s6){
		isSkill1Createable =s1;
		isSkill2Createable =s2;
		isSkill3Createable =s3;
		isSkill4Createable =s4;
		isSkill5Createable =s5;
		isSkill6Createable =s6;
	}
	//////////////////////////////////////////////////////////////////////////
	
	//////////////////////paint, process, event///////////////////////////////
	public void paint(Image img){
		Graphics g2d = (Graphics2D)img.getGraphics();
		
	
		////////////// hpboard -> hpbar -> icon -> hpglass///////////////
		g2d.drawImage(img_hp, 10,(int)(this.getHeight()-130), 400,400*3/10,null);	

		g2d.setColor(Color.red);
		g2d.fillArc(11, (int)(this.getHeight()-120), 100, 100, 230, (int)(360*go.hp/go.hpMax));
		
		g2d.drawImage(gc.getCharacter().matchSkill(gc.getCharacter().getSkillCode(1)).getSkillIcon(isSkill1Createable), 120, 706, 35, 35,this);
		g2d.drawImage(gc.getCharacter().matchSkill(gc.getCharacter().getSkillCode(2)).getSkillIcon(isSkill2Createable), 120+39, 706, 35, 35,this);
		g2d.drawImage(gc.getCharacter().matchSkill(gc.getCharacter().getSkillCode(3)).getSkillIcon(isSkill3Createable), 120+39*2, 706, 35, 35,this);
		g2d.drawImage(gc.getCharacter().matchSkill(gc.getCharacter().getSkillCode(4)).getSkillIcon(isSkill4Createable), 120+39*3, 706, 35, 35,this);
		g2d.drawImage(gc.getCharacter().matchSkill(gc.getCharacter().getSkillCode(5)).getSkillIcon(isSkill5Createable), 119+39*4, 706, 35, 35,this);
		g2d.drawImage(gc.getCharacter().matchSkill(gc.getCharacter().getSkillCode(6)).getSkillIcon(isSkill6Createable), 119+39*5, 706, 35, 35,this);
		
		g2d.drawImage(img_hpglass, 10,(int)(this.getHeight()-130), 400,400*3/10,null);	
		///////////////////////////////////////////////////////////////////////////
	}
	
	public void process(){
		
	}
	
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
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
	////////////////////////////////////////////////////////////////////
}
