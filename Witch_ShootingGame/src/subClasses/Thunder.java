package subClasses;

import gameScene.GameScene;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;

public class Thunder extends Effect {
	
	/*////////////////////super data//////////////////////////////////
	Constants constants;
	GameScene gc;
	GameObject go;
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
	
	/////////////////////////////데이터//////////////////////////////
	/////////////////////////이미지 데이터////////////////////////////
	Image[] imgStore;
	Image img;
	/////////////////////////////////////////////////////////////////////

	/////////////////////////////프레임 데이터//////////////////////////
	int fps;
	int delay;
	////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////
	
	////////////////////////////메서드///////////////////////////////////////
	/////////////////////////construct/////////////////////////////////////
	public Thunder(GameScene gameScene,int wantSize, int delay)
	{
		gc = gameScene;
		this.wantSize = wantSize;
		this.delay =delay;
		init();
		initImg();
	}
	public Thunder(Image[] imgSet , int wantSize, int delay)
	{
		this.imgStore= imgSet;
		this.wantSize = wantSize;
		this.delay =delay;
		init();
		initImg();
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
		fps = 3;
		cnt = -delay*fps;
	}
	public void initImg(){
		imgStore = gc.getImgBox().thunder_imgStore;
		img = gc.getImgBox().thunder_img;
	}
	//////////////////////////////////////////////////////////////////////
	
	///////////////////////////process control///////////////////////////
	public void activate()
	{
		cnt = -delay*fps;
		setVisible(true);
		this.setPhase(1);
		this.setProcessStop(false);
		IS_USE=true;
		setCreateable(false);
		setDeleteable(false);

	}
	public void deactivate()
	{
		setVisible(false);
		this.setPhase(ENDPHASE);
		this.setProcessStop(true);
		setDeleteable(true);
	}
	////////////////////////////////////////////////////////////////////////
	
	///////////////////////is, get, set values///////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////
	
	///////////////////////paint, process, event////////////////////////////
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		if(this.isVisible() ==true){
//			int ddohyeonbabo = 21;
			g.drawImage(img, 
					(int)(posx-img.getWidth(null)/2*sizeScale/100*wantSize/img.getWidth(null)) ,
					(int)(posy-img.getHeight(null)*sizeScale/100*wantSize/img.getHeight(null)*9/10),
					(int)(img.getWidth(null)*sizeScale/100*wantSize/img.getWidth(null)),
					(int)(img.getHeight(null)*sizeScale/100*wantSize/img.getHeight(null)),
					null);		
		}
	}

	public void imgProcess()
	{
		if(cnt>0)
		img = imgStore[cnt/fps%imgStore.length];
	}
	
	@Override
	public void process() {
		// TODO Auto-generated method stub
		super.process();
		if(!this.isProcessStop())
		{
			if(phase ==0){
				
			}
			else if(phase ==1){
			
				if(cnt < (imgStore.length)*fps){
					
					imgProcess();
					cnt++;
				}
				else{
					setPhase(ENDPHASE);
				}
			}
			else if(phase == ENDPHASE){
				this.deactivate();
			}
		}
	}
	////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////
}
