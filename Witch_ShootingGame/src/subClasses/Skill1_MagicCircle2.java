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

import data.Constants;

public class Skill1_MagicCircle2 extends Effect{

	/*//////////////////////////////super data//////////////////////////////////////////
	Constants constants;
	GameScene gc;
	///////////////////////////process flags/////////////////////////////////
	boolean IS_DELETEABLE;
	boolean IS_PROCESSSTOP;
	boolean IS_USE;
	boolean IS_CHARGED;
	
	int cnt;
	int phase;
	final int ENDPHASE = 9999;
	//////////////////////////////////////////////////////////////////////////
	
	//////////////////////////components data/////////////////////////////////
	double posx,posy;
	int chargeLevel;
	double extraCharge;
	
	double wantSize;
	double sizeScale;
	
	double castingSpeed;
	
	Rectangle ridge;
	/////////////////////////////////////////////////////////////////////////
	
	*////////////////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////데이터///////////////////////////////////////////
	PointerInfo ms;
	
	////////////////////////////컴포넌트 데이터///////////////////////////////////////////
	Effect mcs;
	///////////////////////////////////////////////////////////////////////////////////////
	
	

	//////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////메서드////////////////////////////////////////////
	/////////////////////////////construct///////////////////////////////////////////
	public Skill1_MagicCircle2(GameScene gameScene, GameObject gameObject){
		gc = gameScene;
		go =gameObject;
		this.castingSpeed = go.castingSpeed;
		init();
		initComp();
	}
	public void init()
	{
		/*//////////////////////////////////////////
		constants = new Constants();

		IS_CREATEABLE = true;
		IS_DELETEABLE = false;
		IS_PROCESSSTOP =true;
		IS_USE=false;
		IS_CHARGED=false;
		
		posx=0;
		posy=0;
		chargeLevel=0;
		extraCharge = constants.extraCharge;
		cnt=0;
		
		sizeScale=constants.scaleSize;
		castingSpeed =100;
		phase =0;
		
		ridge = new Rectangle(0,0,0,0);
		*/////////////////////////////////////////
		super.init();
		
		ms = MouseInfo.getPointerInfo();
	}
	
	public void initComp(){
		mcs = new MagicCircleStatic(go,200,gc.getImgBox().skill1_mc2_mcs,6,this.castingSpeed);
	}
	////////////////////////////////////////////////////////////////////////////////////////////
	
	///////////////////////process control////////////////////////////////////
	public void activate()
	{
		setPhase(1);
		setVisible(true);
		setProcessStop(false);
		setDeleteable(false);
		setCreateable(false);
		IS_USE = true;
	}

	public void deactivate()
	{
		setPhase(ENDPHASE);
		setVisible(false);
		setProcessStop(true);
		setDeleteable(true);
		IS_USE =false;
	}
	//////////////////////////////////////////////////////////////////////////
	
	/////////////////////////is, get, set values///////////////////////////////
	public void setPhase(int n)
	{
		phase = n;
	}
	//////////////////////////////////////////////////////////////////////
	
	//////////////////////paint, process, event////////////////////////////
	public void paint(Image img)
	{
		if(isVisible())
			mcs.paint((Graphics2D)img.getGraphics());
	}
	
	public void process()
	{
		if(!isProcessStop()){
			if(phase==0){
			}
			else if(phase==1){
				if(mcs.isCreateable())
					mcs.activate();
				
				ms = MouseInfo.getPointerInfo();
				posx= ms.getLocation().getX();
				posy= ms.getLocation().getY();
			}
			else if(phase == ENDPHASE){
				
			}
		}
		
		mcs.process();	
		mcs.setPos(posx, posy);  // 원래 static은 받은 go를 따라가지만 위치 강제지정
	}
	//////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////

}


