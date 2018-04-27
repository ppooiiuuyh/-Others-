package subClasses;

import gameScene.GameScene;

import javax.swing.JPanel;

import data.Constants;

public class GameObject extends JPanel{
	
	////////////////////////데이터//////////////////////////////////////
	Constants constants;
	GameScene gc;
	
	///////////////////////프로세스 플레그////////////////////
	boolean IS_VISIBLE;
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
	/////////////////////////////////////////////////////////////////
	
	/////////////////메서드///////////////////////////////////////
	//////////////construct////////////////////////////////////
	public GameObject(){
		
	}
	public GameObject(GameScene gameScene){
		this.gc = gameScene;
	}
	public void init(){
		constants = new Constants();
		
		IS_PROCESSSTOP = true;
		IS_USE = false;
		
		hp =0;
		hpMax =0;
		coolTimeReduce =0;
		defaultCoolTimeReduce =0;
		defaultCoolTimeReduceTemp =0;
		defaultHpMax =0;
		defaultHpMaxTemp =0;
		defense =0;
		defaultDefense =0;
		defaultDefenseTemp =0;
		moveSpeed =0;
		defaultMoveSpeed =0;
		defaultMoveSpeedTemp =0;
		castingSpeed =100;
		defaultCastingSpeed =100;
		defaultCastingSpeedTemp =100;
		
		posx=0;
		posy=0;
		sizeScale =constants.scaleSize;
		
	}
	/////////////////////////////////////////////////////////////
	
	////////////////////process control//////////////////////////
	public void activate(){
		this.IS_USE = true;
		this.IS_PROCESSSTOP= false;
		this.setVisible(true);
	}
	
	public void deActivate(){
		this.IS_USE = false;
		this.IS_PROCESSSTOP= true;
		this.setVisible(false);
	}
	/////////////////////////////////////////////////////////////
	
	/////////////////////is, get, set values////////////////////////
	public double getHp(){
		return hp;
	}
	public double getHpMax(){
		return hpMax;
	}
	public double getPosx(){
		return posx;
	}
	public double getPosy(){
		return posy;
	}
	public void setSizeScale(int n){
		sizeScale =n;
	}
	public void setMoveSpeed(double n){
		defaultMoveSpeedTemp = n;
	}
	//////////////////////////////////////////////////////////////
}
