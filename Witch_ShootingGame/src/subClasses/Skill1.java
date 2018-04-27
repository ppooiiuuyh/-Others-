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

import data.Constants;


public class Skill1 extends Skill {
	/*///////////////////////////////super data//////////////////////////////////////////////
	Constants constants;
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
	*////////////////////////////////////////////////////////////////////////////////////////////

	////////////////////////////////////데이터///////////////////////////////
	///////////////////////////////이미지 데이터/////////////////////////////////
	Effect mc;
	Effect[] th;
	Effect mc2;
	//////////////////////////////////////////////////////////////////////////
	
	
	/////////////////////////////메서드/////////////////////////////////////////
	////////////////////////////construct/////////////////////////////////////
	public Skill1(GameScene gameScene,GameObject gameObject){
		super(gameScene,gameObject);
		init();
		initComp();

	}
	
	public void init()
	{
		/*//////////////////////////////////////////////
		constants = new Constants();
		phase =0;
		coolTime = 0;
		coolTimeChecker = -1;
		
		sizeScale =constants.scaleSize;
		skillCode =0;
		hitBox = new Vector<Rectangle>();
		 *///////////////////////////////////////////////////
		super.init();

		skillCode = 1;
		coolTime = (long) (5*1000*(100-go.coolTimeReduce)/100);
	}
	
	public void initComp(){
		skillIconImg = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("skillicon_thunder.png"));
		skillIconImgDeactive = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("skillicon_thunderDeactive.png"));
		
		mc = new Skill1_MagicCircle(gc,go);
		th = new Effect[]{new Thunder(gc,300,3),new Thunder(gc,350,12),new Thunder(gc,400,21)};
		mc2 = new Skill1_MagicCircle2(gc,go);
	}
	//////////////////////////////////////////////////////////////////////////////////
	
	////////////////////////////////process control////////////////////////////////
	public void activate()
	{
		IS_USE = true;
		setVisible(true);
		setProcessStop(false);
		setCreateable(false);
		setDeleteable(false);
		setPhase(1);

		coolTimeChecker = System.currentTimeMillis();
	}
	public void deactivate()
	{
		IS_USE =false;
		setVisible(false);
		setProcessStop(true);
		setDeleteable(true);
		setPhase(ENDPHASE);
		
		mc.deactivate();
		mc2.deactivate();
		th[0].deactivate();
		th[1].deactivate();
		th[2].deactivate();
	}
	//////////////////////////////////////////////////////////////////////////////

	///////////////////////////////////is, get, set values/////////////////////////
	public Effect getEffect(int n)
	{
		if(n == 1)
			return mc;
		
		else
			return null;
	}
	///////////////////////////////////////////////////////////////////////////////////////
	
	///////////////////////////////paint, process, event//////////////////////////
	public void paint(Image img)
	{
		mc.paint(img);
		mc2.paint(img);
		for(int i=0; i<th.length; i++)
			th[i].paint(img.getGraphics());
	}
	

	public void process()
	{
		if(coolTimeChecker >=0)
			if(System.currentTimeMillis() - coolTimeChecker >= coolTime)
				IS_CREATEABLE = true;

		
		if(this.IS_PROCESSSTOP && this.IS_CREATEABLE && this.IS_USE)
			this.deactivate();
		
	
		if(phase==0){
		}
		else if(phase==1){

			if(mc.isCreateable())
				mc.activate();
			if(mc.isCharged()){
				if(mc2.isCreateable()){
					mc2.activate();
				}
			}

		}
		else if(phase==2){

			//if(IS_CREATEABLE == false)
			//	IS_CREATEABLE=true;
			if(mc.isUse() && !mc.isProcessStop()){
				mc2.setPhase(ENDPHASE);
				mc.setPhase(ENDPHASE);
			}
		

			if(mc.chargeLevel==0){
				this.deactivate();	
				IS_CREATEABLE = true;
			}
			else if(mc.chargeLevel ==1){		
				if(th[0].isCreateable())
					th[0].activate();
				
				if(th[0].isDeleteable()){
					this.deactivate();
				}
			}
			else if(mc.chargeLevel ==2){
				if(th[0].isCreateable())
					th[0].activate();
				
				if(th[1].isCreateable())
					th[1].activate();
				
				if(th[1].isDeleteable()){
					this.deactivate();
				}
			}
			else if(mc.chargeLevel ==3){
				if(th[0].isCreateable())
					th[0].activate();
				
				if(th[1].isCreateable())
					th[1].activate();
				
				if(th[2].isCreateable())
					th[2].activate();
				
				if(th[2].isDeleteable()){
					this.deactivate();
				}
			}				
		}
		if(phase == ENDPHASE){
			
		}
		

		mc.process();
		mc2.process();
	
		for(int i=0; i<th.length; i++){
			th[i].setPos(mc2.getPosx(), mc2.getPosy());
			th[i].process();
		}		
	}
	
	public void mousePressed(MouseEvent me)
	{
		
	}
	
	public void keyPressed(KeyEvent ke)
	{
		if(!isCreateable()){
			activate();
			setPhase(1);
		}
	}
	public void keyReleased(KeyEvent ke)
	{
		setPhase(2);
	}
	
	
	
	
	
	

}




