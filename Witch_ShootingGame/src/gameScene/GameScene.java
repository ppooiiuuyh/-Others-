package gameScene;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import data.Constants;
import data.GameImages;
import subClasses.Skill1;
import main.GameManager;

public class GameScene extends JPanel implements Runnable {

	
	//-----------------데이터영역-----------------//
	GameManager gm;
	GameImages gci;
	
	Image dumpImg ;

	////////////////flags///////////////////////
	//key flag들은 가능하면 scene에서만 처리
	boolean IS_ENTER_DOWN = false;
	boolean IS_ESCAPE_DOWN = false;
	boolean IS_A_DOWN = false;
	boolean IS_S_DOWN = false;
	boolean IS_D_DOWN = false;
	boolean IS_F_DOWN = false;
	boolean IS_G_DOWN = false;
	boolean IS_H_DOWN = false;
	boolean IS_I_DOWN = false;
	boolean IS_K_DOWN = false;
	boolean IS_C_DOWN = false;
	boolean IS_BUTTON3_DOWN = false;
	
	boolean IS_USE ;		  //처음 여부
	boolean IS_STOP;    	  //완전 종료
	boolean IS_PROCESSSTOP; //일시정지
	////////////////////////////////////////////
	Constants constants;
	
	////////////////thread control///////////////
	Thread _runner;	
	long startTime;
	long timeGap;
	long fps;
	int cnt;
	/////////////////////////////////////////////
	
	
	/////////////////phase control////////////////
	int phase;
	public final int SCENEPHASE = 1;
	public final int MENUPHASE = 2;
	////////////////////////////////////////////
	
	//--------------------메서드----------------------------//
	/////////////////////////////construct///////////////////////
	public GameScene(GameManager gameManager){
		gm = gameManager;
		gci = gm.getImgBox();
	}

	public void init(){
		constants = new Constants();
		
		dumpImg = createImage(this.getWidth(),this.getHeight());
		
		this.setBounds(0,0,gm.getWidth(),gm.getHeight());
		this.setLayout(null);
		
		IS_USE = false;		  //처음 여부
		IS_STOP =true;    	  //완전 종료
		IS_PROCESSSTOP =true; //일시정지
		
		_runner = new Thread(this);	
		startTime =0;
		timeGap=0;
		fps=constants.fps;
		cnt=0;
		
		phase =0;
	}
	
	//////////////////////////////////////////////////////////////
	
	
	//////////////////////////process control////////////////////
	public void activate()
	{
		gm.remove(this);
		if(IS_USE == true){
			_runner.stop();
			_runner = new Thread(this);
		}
		gm.add(this);
		this.IS_USE = true;
		this.IS_STOP = false;
		this.IS_PROCESSSTOP = false;
		_runner.start();
	}
	public void deactivate()
	{
		this.IS_STOP = true;
		this.IS_PROCESSSTOP = true; 
		if(IS_USE==true){		
			this._runner.stop();
			IS_USE=false;
		}
		gm.remove(this);
	}
	
	public void pause() 
	{
		this.IS_PROCESSSTOP = true;

	}
	public void restart()
	{
		this.IS_PROCESSSTOP = false;
	}
	//////////////////////////////////////////////////////////////////
	
	
	
	
	////////////////////////get, set flags//////////////////////////
	public boolean isProcessStop()
	{
		return IS_PROCESSSTOP;
	}
		
	public subClasses.Character getCharacter()
	{
		return null;
	}
	
	public GameManager getGameManager(){
		return gm;
	}
	public GameImages getImgBox(){
		return gci;
	}
	
	public void setProcessStop(boolean b)
	{
		this.IS_PROCESSSTOP = b;
		
	}
	public void setPhase(int n){
		phase = n;
	}
	
	////////////////////////////////////////////////////////////////
	
	
	
	////////////paint , process, run///////////////////////////////
	public void paint(Image img)
	{
		
	}
	public void paint(Graphics g)
	{
		
	}
	public void process()
	{
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
			
	}
	//////////////////////////////////////////////////////////////////
	

	
	
	
	/////////////////////////event Control////////////////////////////
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
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
	
	

	public void keyPressed(KeyEvent ke) {
		// TODO Auto-generated method stub
		
		if(ke.getKeyCode() == ke.VK_A)
			IS_A_DOWN =true;
		if(ke.getKeyCode() == ke.VK_S)
			IS_S_DOWN =true;
		if(ke.getKeyCode() == ke.VK_D)
			IS_D_DOWN =true;
		if(ke.getKeyCode() == ke.VK_F)
			IS_F_DOWN =true;
		if(ke.getKeyCode() == ke.VK_G)
			IS_G_DOWN =true;
		if(ke.getKeyCode() == ke.VK_H)
			IS_H_DOWN =true;
		if(ke.getKeyCode() == ke.VK_I)
			IS_I_DOWN =true;
		if(ke.getKeyCode() == ke.VK_K)
			IS_K_DOWN =true;
		if(ke.getKeyCode() == ke.VK_C)
			IS_C_DOWN =true;
		if(ke.getKeyCode() == ke.VK_ESCAPE)
			IS_ESCAPE_DOWN =true;
		if(ke.getKeyCode() == ke.VK_ENTER)
			IS_ENTER_DOWN =true;
		
	}

	public void keyReleased(KeyEvent ke) {
		// TODO Auto-generated method stub
		if(ke.getKeyCode() == ke.VK_A)
			IS_A_DOWN =false;
		if(ke.getKeyCode() == ke.VK_S)
			IS_S_DOWN =false;
		if(ke.getKeyCode() == ke.VK_D)
			IS_D_DOWN =false;
		if(ke.getKeyCode() == ke.VK_F)
			IS_F_DOWN =false;
		if(ke.getKeyCode() == ke.VK_G)
			IS_G_DOWN =false;
		if(ke.getKeyCode() == ke.VK_H)
			IS_H_DOWN =false;
		if(ke.getKeyCode() == ke.VK_I)
			IS_I_DOWN =false;
		if(ke.getKeyCode() == ke.VK_K)
			IS_K_DOWN =false;
		if(ke.getKeyCode() == ke.VK_C)
			IS_C_DOWN =false;
		if(ke.getKeyCode() == ke.VK_ESCAPE)
			IS_ESCAPE_DOWN =false;
		if(ke.getKeyCode() == ke.VK_ENTER)
			IS_ENTER_DOWN =false;
		
	}
	

	
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	////////////////////////////////////////////////////////////
	
}
