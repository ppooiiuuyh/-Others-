package subClasses;

import gameScene.GameScene;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

public class MagicCircleGrowth extends Effect {


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
	
	//////////////////////////데이터/////////////////////////////////////////
	///////////////////////이미지 데이터//////////////////////////////////////
	Image img;
	//////////////////////////////////////////////////////////////////////////
	
	////////////////////////이텍트 데이터//////////////////////////////////////
	int degree;
	int rpf; // rotate degree per frame
	int wantSize;
	double growSpeed;
	double chargeTime;
	double sizeScaleTemp;
	////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////
	
	
	//////////////////////메서드////////////////////////////////////////////
	///////////////////////construct///////////////////////////////////////
	public MagicCircleGrowth(GameObject gameObject, int wantSize,Image img,int rpf,double castingSpeed)
	{
		this.go = gameObject;
		this.wantSize = wantSize;
		this.img = img;
		this.castingSpeed = castingSpeed;
		this.rpf = (int) (rpf*castingSpeed/100);
		init();	
	}

	
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		/*///////////////////////////////////////////////
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
		*/////////////////////////////////////
		super.init();
		
		sizeScaleTemp=0;
		
		degree =0;

		growSpeed =(2*sizeScale/100*castingSpeed/100);
		chargeTime = sizeScale/growSpeed + extraCharge*100/castingSpeed;
	}
	//////////////////////////////////////////////////////////////////////
	
	////////////////process control///////////////////////////////////////
	@Override
	public void activate() {
		// TODO Auto-generated method stub
		super.activate();
		cnt=0;
		IS_USE = true;
		setPhase(1);
		setVisible(true);
		setProcessStop(false);
		setDeleteable(false);
		setCreateable(false);
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		super.deactivate();
		setPhase(ENDPHASE);
		setVisible(false);
		setProcessStop(true);
		setDeleteable(true);
	}
	//////////////////////////////////////////////////////////////////////////
	
	////////////////paint, process, event///////////////////////////////////////
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);

		if(this.isVisible() == true){
			Graphics2D g2d = (Graphics2D)g;
	
			AffineTransform a2 = AffineTransform.getTranslateInstance(posx, posy);
			g2d.transform(a2);
			AffineTransform a = AffineTransform.getRotateInstance(Math.PI*degree/360*2);
			g2d.transform(a);
			
			
			g2d.drawImage(img,
						(int)(-img.getWidth(null)/2*sizeScaleTemp/100*wantSize/img.getWidth(null)) ,
						(int)(-img.getHeight(null)/2*sizeScaleTemp/100*wantSize/img.getHeight(null)),
						(int)(img.getWidth(null)*sizeScaleTemp/100*wantSize/img.getWidth(null)),
						(int)(img.getHeight(null)*sizeScaleTemp/100*wantSize/img.getHeight(null)),
						null);
		}
	}
	
	@Override
	public void process() {
		// TODO Auto-generated method stub		
		if(!isProcessStop()){
			
			
			if(phase == 0){
				cnt =0;
			}
			else if(phase == 1){
				cnt++;
				
				setPos(go.getPosx(),go.getPosy());
				degree += rpf;
				if(sizeScaleTemp <= sizeScale){
					sizeScaleTemp += growSpeed;
				}
				if(sizeScaleTemp >sizeScale){
					sizeScaleTemp = sizeScale;
				}
				if(cnt >= chargeTime){
					deactivate();
				}
			}
		}
	}
	///////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////////////
}
