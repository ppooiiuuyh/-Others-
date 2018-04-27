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

public class Skill1_MagicCircle extends Effect{
	
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
	
	////////////////////////////////데이터////////////////////////////////////////
	
	//////////////////////////컴포넌트 데이터///////////////////////////////////////
	Effect mcg ;
	Effect mce ;
	Effect mcs ;
	
	Effect mcg2;
	Effect mce2 ;
	Effect mcs2 ;
	
	Effect mcg3 ;
	Effect mce3 ;
	Effect mcs3 ;
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////메서드////////////////////////////////////
	////////////////////////////construct//////////////////////////////////
	public Skill1_MagicCircle(GameScene gameScene,GameObject gameObject){
		gc = gameScene;
		go = gameObject;;
		init();
		initComp();

	}
	
	public void init()
	{
		/*////////////////////////////////////////////////
		constants = new Constants();

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
		*///////////////////////////////////////////////////
		super.init();
		
		this.setVisible(false);
		this.castingSpeed = go.castingSpeed;
	}
	public void initComp(){
		mcg = new MagicCircleGrowth(go,200,gc.getImgBox().skill1_mcg,6,this.castingSpeed);
		mce = new MagicCircleEffect(go,250,gc.getImgBox().skill1_mce,6,this.castingSpeed);
		mcs = new MagicCircleStatic(go,200,gc.getImgBox().skill1_mcs,1,this.castingSpeed);

		mcg2 = new MagicCircleGrowth(go,410,gc.getImgBox().skill1_mcg2,-6,this.castingSpeed);
		mce2 = new MagicCircleEffect(go,500,gc.getImgBox().skill1_mce2,-6,this.castingSpeed);
		mcs2 = new MagicCircleStatic(go,410,gc.getImgBox().skill1_mcs2,-1,this.castingSpeed);

		mcg3 = new MagicCircleGrowth(go,440,gc.getImgBox().skill1_mcg3,6,this.castingSpeed);
		mce3 = new MagicCircleEffect(go,500,gc.getImgBox().skill1_mce3,6,this.castingSpeed);
		mcs3 = new MagicCircleStatic(go,440,gc.getImgBox().skill1_mcs3,1,this.castingSpeed);
	}
	//////////////////////////////////////////////////////////////////////////////
	
	///////////////////////////process control/////////////////////////////////////////
	public void activate()
	{
		phase =1;
		setVisible(true);
		setProcessStop(false);
		IS_CHARGED=false;
		IS_USE = true;
		chargeLevel =0;
		setDeleteable(false);
		setCreateable(false);
	}
	public void deactivate()
	{
		IS_USE = false;
		setVisible(false);
		setProcessStop(true);
		setDeleteable(true);
		setCreateable(true);
		setPhase(ENDPHASE);
		
		mcg.deactivate();
		mcg2.deactivate();
		mcg3.deactivate();
		mce.deactivate();
		mce2.deactivate();
		mce3.deactivate();
		mcs.deactivate();
		mcs2.deactivate();
		mcs3.deactivate();
	}
	////////////////////////////////////////////////////////////////////////////

	//////////////////////////is, get, set///////////////////////////////////////	

	////////////////////////////////////////////////////////////////////////////
	
	
	/////////////////////////paint, process, event//////////////////////////////
	public void paint(Image img){
		if(isVisible()){
			mcs3.paint((Graphics2D)img.getGraphics());
			mcg3.paint((Graphics2D)img.getGraphics());
			mce3.paint((Graphics2D)img.getGraphics());
		
			mcs2.paint((Graphics2D)img.getGraphics());
			mcg2.paint((Graphics2D)img.getGraphics());
			mce2.paint((Graphics2D)img.getGraphics());
		
			mcs.paint((Graphics2D)img.getGraphics());
			mcg.paint((Graphics2D)img.getGraphics());
			mce.paint((Graphics2D)img.getGraphics());
		}
	}
	
	public void compProcess(){
		mce.process();
		mcg.process();
		mcs.process();
		

		mce2.process();
		mcg2.process();
		mcs2.process();
		

		mce3.process();
		mcg3.process();
		mcs3.process();
	}

	////////////////charge level 단위로 phase 구성//////////////////////
	public void process(){
		if(!isProcessStop()){
			if(phase==0){
				
			}
			else if(phase ==1){
				if(mcg.isCreateable())
					mcg.activate();
				if(mcg.isDeleteable()){
					if(mce.isCreateable()){
						mce.activate();}
					if(mcs.isCreateable()){
						mcs.activate();
						IS_CHARGED =true;
						this.chargeLevel=1;
					}
					setPhase(2);
				}
			}
			else if(phase ==2){
				if(mcg2.isCreateable())
					mcg2.activate();
				if(mcg2.IS_DELETEABLE){
					if(mce2.isCreateable())
						mce2.activate();
					if(mcs2.isCreateable()){
						mcs2.activate();
						IS_CHARGED =true;
						this.chargeLevel=2;
					}
					setPhase(3);
				}
			}
			else if(phase ==3){
				
				if(mcg3.isCreateable())
					mcg3.activate();
				if(mcg3.isDeleteable()){
					if(mce3.isCreateable())
						mce3.activate();
					if(mcs3.isCreateable()){
						mcs3.activate();
						IS_CHARGED =true;
						this.chargeLevel=3;
					}
					setPhase(ENDPHASE);
				}
			}
			else if(phase ==ENDPHASE){
				mcg.deactivate();
				mcg2.deactivate();
				mcg3.deactivate();
				mce.deactivate();
				mce2.deactivate();
				mce3.deactivate();
			}
				
			compProcess();
		}
	}
	
	public void mousePressed(MouseEvent me){
		
	}
	public void keyPressed(KeyEvent ke){
	
	}
	public void keyReleased(KeyEvent ke){
		
	}
	//////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////
}


