package subClasses;

import gameScene.GameScene;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import data.Constants;

public class BackGround_Lobby extends Effect {

	/*/////////////////////super data////////////////////////////////
	Constants constants;
	GameScene gc;
	///////////////////////////process flags/////////////////////////////////
	boolean IS_DELETEABLE;
	boolean IS_PROCESSSTOP;
	boolean IS_USE;
	boolean IS_CHARGED;
	
	int cnt;
	//////////////////////////////////////////////////////////////////////////
	
	//////////////////////////components data/////////////////////////////////
	double posx,posy;
	int chargeLevel;
	double extraCharge;

	double sizeScale;
	double castingSpeed;
	int phase;
	
	Rectangle ridge;
	*//////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////데이터////////////////////////////////////////
	
	/////////////////////////////이미지////////////////////////////////////////
	Image[] imgStore;
	
	Image img;
	Image img_glass;
	////////////////////////////////////////////////////////////////////
	
	///////////////////////////////////프레임///////////////////////////////////
	int fps; // 60 per 1sec
	/////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////
	
	
	//////////////////////////메서드//////////////////////////////////////////
	////////////////////////construct////////////////////////////////////////
	public BackGround_Lobby(GameScene gameScene){
		super(gameScene);
		init();
	}
	public void init() {
		super.init();
		setSize(gc.getWidth(),gc.getHeight());
		
		imgStore = new Image[] { Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("lobby1.jpg")),
										Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("lobby3.jpg")),
										Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("lobby4.jpg")),
										Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("lobby5.jpg"))};

		img = imgStore[0];
		img_glass =Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("backgroundglass.png"));

		fps =300; // 60 per 1sec
	}
	////////////////////////////////////////////////////////////////////////
	
	//////////////////////////process control//////////////////////////////
	public void activate()
	{
		init();
		setVisible(true);
		IS_USE = true;
		setProcessStop(false);
	}
	public void deactivate()
	{
		cnt =0;
		setVisible(false);
		setDeleteable(true);
		setProcessStop(true);
	}
	///////////////////////////////////////////////////////////////////////
	
	////////////////////paint, process////////////////////////////////////
	@Override
	public void paint(Image img) {
		// TODO Auto-generated method stub
		super.paint(img);
		if(this.isVisible()){
			img.getGraphics().drawImage(this.img, 0, 0,(int)this.getWidth(),(int)this.getWidth(),null);
			img.getGraphics().drawImage(this.img_glass, 0, 0,(int)this.getWidth(),(int)this.getWidth(),null);
		}
	}
	
	public void imgProcess(){
		img = imgStore[cnt/fps%imgStore.length];
	}
	public void process() {
		if(cnt >= fps*imgStore.length)
			cnt =0;
		cnt++;
		
		imgProcess();
	}
	//////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////////////

}
