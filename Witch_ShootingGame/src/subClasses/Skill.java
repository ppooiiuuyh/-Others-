package subClasses;

import gameScene.GameScene;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.Vector;

import javax.swing.JPanel;

import data.Constants;


public class Skill extends JPanel{
	////////////////////////////////데이터//////////////////////////////////////////////
	Constants constants;
	GameScene gc;
	GameObject go;
	
	/////////////////////////////프로세스 플레그//////////////////////////////////////
	boolean IS_USE = false;
	boolean IS_VISIBLE = false;
	boolean IS_PROCESSSTOP = true;
	boolean IS_CREATEABLE = true;
	boolean IS_DELETEABLE = false;
	
	int phase;
	final int ENDPHASE =9999;
	///////////////////////////////////////////////////////////////////////////////
	
	
	/////////////////////////이미지 데이터//////////////////////////////////////////////////
	Image skillIconImg;
	Image skillIconImgDeactive;
	/////////////////////////////////////////////////////////////////////////////////////

	////////////////////////스킬 데이터//////////////////////////////////////////////////////////
	long coolTime;
	long coolTimeChecker;

	double sizeScale;
	
	int skillCode;
	Vector<Rectangle> hitBox;
	/////////////////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////메서드///////////////////////////////////////////////
	////////////////////////////////construct//////////////////////////////////////////
	public Skill()
	{
	}
	public Skill(GameScene gameScene)
	{
		this.gc = gameScene;
	}
	public Skill(GameObject gameObject)
	{
		this.go = gameObject;
	}
	public Skill(GameScene gameScene,GameObject gameObject)
	{
		this.gc = gameScene;
		this.go = gameObject;
	}
	public void init()
	{
		constants = new Constants();
		phase =0;
		coolTime = 0;
		coolTimeChecker = -1;
		
		sizeScale =constants.scaleSize;
		skillCode =0;
		hitBox = new Vector<Rectangle>();
	}
	public void initImg(){
		skillIconImg = Toolkit.getDefaultToolkit().getImage("skillIcon\\noSkill.png");
		skillIconImgDeactive = Toolkit.getDefaultToolkit().getImage("skillIcon\\noSkill.png");	
	}
	////////////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////process control///////////////////////////////////////
	public void activate()
	{
	}
	public void deactivate()
	{
	}
	////////////////////////////////////////////////////////////////////////////
	
	/////////////////////////////is, get, set values/////////////////////////////
	public boolean isProcessStop()
	{
		return IS_PROCESSSTOP;
	}
	public boolean isCreateable()
	{
		return IS_CREATEABLE;
	}
	public boolean isDeleteAble() {
		// TODO Auto-generated method stub
		return IS_DELETEABLE;
	}
	public boolean isUse()
	{
		return IS_USE;
	}
	public boolean isHit(GameObject gameObj){
		boolean hitChecker= false;
		return hitChecker;
	}
	public Effect getEffect(int n)
	{	
		return null;
	}
	public Image getSkillIcon(int n)
	{
		if(n ==1)
			return skillIconImg;
		
		else
			return skillIconImgDeactive;
	}
	public int getSkillCode(){
		return skillCode;
	}
	public void setVisible(boolean b) 
	{
		IS_VISIBLE = b;
	}
	public void setPhase(int n)
	{
		phase = n;
	}
	public void setDeleteable(boolean b){
		IS_DELETEABLE = b;
	}
	public void setCreateable(boolean b){
		IS_CREATEABLE = b;
	}
	public void setSizeScale(int n)
	{
		sizeScale =n;
	}
	public void setProcessStop(boolean b){
		IS_PROCESSSTOP = b;
	}
	/////////////////////////////////////////////////////////////////////////////
	
	///////////////////////////paint, process, event/////////////////////////////
	public void paint(Image img)
	{
	}
	public void process()
	{
	}
	public void mousePressed(MouseEvent me)
	{
	}
	
	public void keyPressed(KeyEvent ke)
	{
	}
	
	public void keyReleased(KeyEvent ke)
	{
	}
	//////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////

}




