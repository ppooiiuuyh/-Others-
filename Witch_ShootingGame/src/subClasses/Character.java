package subClasses;

import gameScene.GameScene;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import data.Constants;

public class Character extends GameObject {
	
	/*////////////////////super data//////////////////////////////////
	Constants constants;
	
	///////////////////////프로세스 플레그////////////////////
	boolean IS_PROCESSSTOP;
	boolean IS_USE;
	////////////////////////////////////////////////////////
	
	
	///////////////////////오브젝트 스테이터스//////////////////////////
	public double hp;
	public double hpMax;
	double coolTimeReduce;
	double defaultCoolTimeReduce;
	double defaultCoolTimeReduceTemp;
	double defaultHpMax;
	double defaultHpMaxTemp;
	double defense;
	double defaultDefense;
	double defaultDefenseTemp;
	double moveSpeed;
	double defaultMoveSpeed;
	double defaultMoveSpeedTemp;
	double castingSpeed;
	double defaultCastingSpeed;
	double defaultCastingSpeedTemp;
	/////////////////////////////////////////////////////////////
	
	//////////////////////오브젝트 데이터//////////////////////////
	double posx,posy;
	double sizeScale;
	//////////////////////////////////////////////////////////////
	*//////////////////////////////////////////////////////////////

	
	
	//////////////////////////데이터/////////////////////////////
	
	////////////////////프로세스 플레그/////////////////////////////
	boolean IS_BUTTON3_DOWN;
	////////////////////////////////////////////////////
	
	///////////////////////이동 데이터///////////////////////
	double curserx =1,cursery =1; 
	double curserDegree=0;
	double restDist =1;
	double v0;
	double decStartDist =100;
	double pos_objectedx=1, pos_objectedy=1;
	double degree =0; // radian
	double stepScale =0;
	double stepScale_underLimit;
	////////////////////////////////////////////////////////
	
	//////////////////////////////////스킬 데이터////////////////////
	int skill1Code =1;
	int skill2Code =0;
	int skill3Code =0;
	int skill4Code =0;
	int skill5Code =0;
	int skill6Code =0;
	/////////////////////////////////////////////////////////////////////
	
	////////////////////////////이미지 데이터///////////////////////////////////////////////
	Image[] imgStore = gc.getImgBox().char_imgStore;
	
	Image img = gc.getImgBox().char_img;

	int moveCounter =0;
	/////////////////////////////////////////////////////////////////////////////////////
	
	////////////////프레임 데이터////////////////////////////////////////////
	int fps =1;
	//////////////////////////////////////////////////////////////////////////
	
	
	///////////////////////////메서드//////////////////////////////////
	//////////////////////////construct///////////////////////////////
	public Character(GameScene gameScene)
	{
		super(gameScene);
		init();
	}
	
	public void init()
	{
		super.init();
		
		IS_BUTTON3_DOWN = false;
		
		defaultDefense = 100;
		defaultDefenseTemp = defaultDefense;
		defaultCastingSpeed = 100;
		defaultCastingSpeedTemp = defaultCastingSpeed;
		defaultHpMax = 100;
		defaultHpMaxTemp = defaultHpMax;
		defaultMoveSpeed = 6;
		defaultMoveSpeedTemp = defaultMoveSpeed;
		defaultCoolTimeReduce = 0;
		defaultCoolTimeReduceTemp =defaultCoolTimeReduce;
		
		hpMax = defaultHpMaxTemp;
		hp = hpMax;
		coolTimeReduce = defaultCoolTimeReduceTemp;
		defense = defaultDefenseTemp;
		moveSpeed = defaultMoveSpeedTemp;
		castingSpeed = defaultCastingSpeedTemp;
		
		curserx =1;
		cursery =1; 
		curserDegree=0;
		restDist =1;
		v0=0;
		decStartDist =100;
		pos_objectedx=1;
		pos_objectedy=1;
		degree =0; // radian
		stepScale =0;
		stepScale_underLimit=0;
		
		skill1Code =1;
		skill2Code =0;
		skill3Code =0;
		skill4Code =0;
		skill5Code =0;
		skill6Code =0;
	}
	//////////////////////////////////////////////////////////////////////
	
	//////////////////////is, get, set values////////////////////////
	public int getSkillCode(int n){
		if(n == 1)
			return skill1Code;
		else if(n == 2)
			return skill2Code;
		else if(n== 3)
			return skill3Code;
		else if(n== 4)
			return skill4Code;
		else if(n== 5)
			return skill5Code;
		else if(n== 6)
			return skill6Code;
		else
			return 0;
	}
	public Skill matchSkill(int skillSlotNumber){
		Skill skillTemp= new Skill(gc,this);
		if(this.getSkillCode(skillSlotNumber) == 1)
			skillTemp = new Skill1(gc,this);

		return skillTemp;
	}
	////////////////////////////////////////////////////////////////
	
	/////////////////////////paint, process, event/////////////////
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;

		AffineTransform a2 = AffineTransform.getTranslateInstance(posx, posy);
		g2d.transform(a2);
		AffineTransform a = AffineTransform.getRotateInstance(curserDegree);
		g2d.transform(a);
		
		g2d.drawImage(img,(int)(-img.getWidth(null)/2*sizeScale/100),(int)(-img.getHeight(null)/2*sizeScale/100),(int)(img.getWidth(null)*sizeScale/100),(int)(img.getHeight(null)*sizeScale/100), null);
	}
	
	public void imgProcess()
	{
		if(restDist >= decStartDist){
			moveCounter  = ++moveCounter%(fps*(imgStore.length-1));
			img = imgStore[moveCounter/fps];
		}
		else{
			img = imgStore[imgStore.length-1];
		}
	}
	public void process()
	{
		///////////////////////좌표처리///////////////////////////
		////////////////////////항상/////////////////////////
		PointerInfo ms = MouseInfo.getPointerInfo();
		curserx = ms.getLocation().x;
		cursery = ms.getLocation().y;
		curserDegree = Math.atan2((cursery-posy),(curserx-posx));
		////////////////////////////////////////////////////////
		
		//////////////////////눌려있는동안/////////////////////////////
		if(IS_BUTTON3_DOWN){
		
			pos_objectedx = curserx;
			pos_objectedy = cursery;
			degree = Math.atan2((pos_objectedy-posy),(pos_objectedx-posx));
			if(stepScale <= stepScale_underLimit)
				stepScale = stepScale_underLimit;
		}
		/////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////
		
		
		///////////////////////////////////move/////////////////////////
		restDist = Math.sqrt(Math.pow(pos_objectedy - posy,2) + Math.pow(pos_objectedx - posx,2));
		if(restDist > decStartDist){
			if(stepScale <= moveSpeed)
				stepScale += moveSpeed/50;
			v0 = stepScale;
		}
		if(restDist <= decStartDist && stepScale >= 0 )
			stepScale -= (Math.pow(v0, 2)/decStartDist)/2 *10/8;
		if(stepScale <0.001)
			stepScale =0;

		
		
		if((posx - pos_objectedx)<-stepScale ||(posx - pos_objectedx)>stepScale)
			posx +=  stepScale*Math.cos(degree);// (pos_objectedx - posx)/Math.abs(pos_objectedx - posx);
		if((posy - pos_objectedy)<-stepScale ||(posy - pos_objectedy)>stepScale)
			posy +=  stepScale*Math.sin(degree); //(pos_objectedy - posy)/Math.abs(pos_objectedy - posy);
		//////////////////////////////////////////////////////////////////////////////
	
	
		//////////////////////////////////imgProcess///////////////////////////////
		imgProcess();
		////////////////////////////////////////////////////////////////////////
	
		/////////////////////////////////status///////////////////////////////////////
		coolTimeReduce = defaultCoolTimeReduceTemp;
		defense = defaultDefenseTemp;
		moveSpeed = defaultMoveSpeedTemp;
		castingSpeed = defaultCastingSpeedTemp;
		hpMax = defaultHpMaxTemp;
		hp = hpMax;
		//////////////////////////////////////////////////////////////////////////////
	}
	
	
	public void mousePressed(MouseEvent me)
	{
		if(me.getButton() == me.BUTTON3)
			IS_BUTTON3_DOWN = true;
	}
	
	public void mouseReleased(MouseEvent me)
	{
		if(me.getButton() == me.BUTTON3)
			IS_BUTTON3_DOWN = false;	
	}
	///////////////////////////////////////////////////////////////////////////////////
}
