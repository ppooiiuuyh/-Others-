package gameScene;

import gameSystem.Interface_Hp;
import gameSystem.Menu_BattleScene;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Vector;

import subClasses.BackGround;
import subClasses.Character;
import subClasses.Effect;
import subClasses.Skill;
import subClasses.Skill1;
import subClasses.Sound;
import main.GameManager;

public class BattleScene extends GameScene{

	
	///////////////오버라이드///////////////////
	
	
	/////////////////////////////////////////
	
	
	
	//////////////컴포넌트////////////////////
	Effect	bg = null;
	Character c = null;
	Menu_BattleScene menu;
	Sound s = null;

	Interface_Hp hi;
	
	Vector<Skill> sk1 = null;
	Vector<Skill> sk2 = null;
	Vector<Skill> sk3 = null;
	Vector<Skill> sk4 = null;
	Vector<Skill> sk5 = null;
	Vector<Skill> sk6 = null;
	int sk1_check;
	int sk2_check;
	int sk3_check;
	int sk4_check;
	int sk5_check;
	int sk6_check;
	/////////////////////////////////////////
	
	
	////////////////////--------------------메서드-------------////////
	//////////////////construct override/////////////////////////////
	//실수를 대비하여 기본생성자는 만들지 못하게한다
	public BattleScene(GameManager gameManager) {
		// TODO Auto-generated method stub
		super(gameManager);
		init();
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		/*/////////////////////////////////////////////////////
	 	this.setBounds(0,0,gm.getWidth(),gm.getHeight());
		this.setLayout(null);
		
		IS_USE = false;		  //처음 여부
		IS_STOP =true;    	  //완전 종료
		IS_PROCESSSTOP =true; //일시정지
		
		
		dumpImg = createImage(this.getWidth(),this.getHeight());
		*//////////////////////////////////////////////////////
		super.init();
		
		bg = new BackGround(this);
		c = new Character(this);
		menu = new Menu_BattleScene(this,gm);
		s = new Sound(getClass().getClassLoader().getResource("boss1.wav"),-1);
		
		hi= new Interface_Hp(this,c);
		
		sk1 = new Vector<Skill>();
		sk2 = new Vector<Skill>();
		sk3 = new Vector<Skill>();
		sk4 = new Vector<Skill>();
		sk5 = new Vector<Skill>();
		sk6 = new Vector<Skill>();
		sk1_check=1;
		sk2_check=1;
		sk3_check=1;
		sk4_check=1;
		sk5_check=1;
		sk6_check=1;
		
		c.setMoveSpeed(6);
		
	}
	//////////////////////////////////////////////////////
	
	
	//////////////////////////process controll/////////////
	@Override
	public void activate() {
		// TODO Auto-generated method stub
		/*/////////////////////////////////////////////
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
		*/////////////////////////////////////////////
		super.activate();
		
		this.setVisible(true);
		dumpImg = createImage(this.getWidth(),this.getHeight());
		
		bg.activate();
		hi.activate();
		menu.activate();
		s.activate();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		/*//////////////////////////		
		this.IS_STOP = true;
		this.IS_PROCESSSTOP = true; 
		if(IS_USE==true){		
			this._runner.stop();
			IS_USE=false;
		}
		gm.remove(this);
		*///////////////////////////
		super.deactivate();
		this.setVisible(false);
		hi.deactivate();
		bg.deactivate();
		menu.deactivate();
		s.deactivate();
	}
	////////////////////////////////////////////////////////
	
	
	
	/////////////////////////get, set flags/////////////////
	public Character getCharacter(){
		return c;
	}
	/////////////////////////////////////////////////////////
	
	
	
	/////////////////////////paint, process, run/////////////
	@Override
	public void paint(Graphics g) {
		
		if(phase == 0){
			
			phase = SCENEPHASE;
		}
		else if(phase == SCENEPHASE){
			if(isVisible()){
				dumpImg = createImage(this.getSize().width,this.getSize().height);
				
				/////////////paint on dump////////////////////////
				/////////////paint background/////////////////////
				bg.paint(dumpImg);
				
				/////////////paint sk1////////////////////////////
				for(int i=0; i<sk1.size(); i++)
					sk1.elementAt(i).paint(dumpImg);
		
				////////////paint character///////////////////////
				c.paint((Graphics2D)dumpImg.getGraphics());
				
				///////////paint hp interface//////////////////////
				hi.paint(dumpImg);
				///////////////////////////////////////////////////
				
				
				
				//////////paint dump///////////////////////////////
				g.drawImage(dumpImg, 0, 0,this);
			}
		}
		else if(phase == MENUPHASE){
			
			////////////paint on dump/////////////////////////
			////////////paint menu glass//////////////////////
			if( menu.gdc <= 90/menu.getGlassThisckness()){
				menu.paintGlass(dumpImg);
			}

			//////////paint menu/////////////////////////
			menu.paint(dumpImg);
			/////////////////////////////////////////////////
			
			
			//////////paint dump/////////////////////////
			g.drawImage(dumpImg, 0, 0,this);
			////////////////////////////////////////////////////
		}
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		super.process(); //do nothing
		
		//////////process before scene phase///////////////
		if(phase == 0){
			phase = SCENEPHASE;
		}
		
		
		if(phase == SCENEPHASE){
			bg.process();
			c.process();
	
			//////////////////////// process skill/////////////////////////
			////////////////check skill Createable///////////////////////
			sk1_check =1;
			for(int i =0; i<sk1.size() ; i++)
				if(!sk1.elementAt(i).isCreateable())
					sk1_check=0;
			sk2_check =1;
			for(int i =0; i<sk2.size() ; i++)
				if(!sk2.elementAt(i).isCreateable())
					sk2_check=0;
			sk3_check =1;
			for(int i =0; i<sk3.size() ; i++)
				if(!sk3.elementAt(i).isCreateable())
					sk3_check=0;
			sk4_check =1;
			for(int i =0; i<sk4.size() ; i++)
				if(!sk4.elementAt(i).isCreateable())
					sk4_check=0;
			sk5_check =1;
			for(int i =0; i<sk5.size() ; i++)
				if(!sk5.elementAt(i).isCreateable())
					sk5_check=0;
			sk6_check =1;
			for(int i =0; i<sk6.size() ; i++)
				if(!sk6.elementAt(i).isCreateable())
					sk6_check=0;
		
			hi.coolTimeUpdate(sk1_check,sk2_check,sk3_check,sk4_check,sk5_check,sk6_check);
			///////////////////////////////////////////////////////////////////////////
			
			///////////////remove deleteable skill////////////////////////////////////
			for(int i=sk1.size()-1; i>=0; i--)
				if(sk1.elementAt(i).isDeleteAble())
					sk1.remove(i);
			for(int i=0; i<sk1.size(); i++)
			sk1.elementAt(i).process();
			//////////////////////////////////////////////////////////////////////
			//////////////////////////////////////////////////////////////////////
		}
		
		if(phase == MENUPHASE){
			menu.process();
		}
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			
			if(!isProcessStop()){
				startTime = System.currentTimeMillis();
					
						
				this.process();
				this.repaint();


				timeGap =System.currentTimeMillis()-startTime;
				fps = 16-timeGap;
				if(fps <0)
					fps =0;
				
				try {
					Thread.sleep(fps);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
				_runner.resume();
			
	
		}
			
	}
	///////////////////////////////////////////////////////////////

	
	////////////////////event control//////////////////////////////
	@Override
	public void mouseClicked(MouseEvent me) {
		// TODO Auto-generated method stub
		super.mouseClicked(me);
		menu.mouseClicked(me);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		super.mouseEntered(arg0);
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		super.mouseExited(arg0);
	}

	@Override
	public void mousePressed(MouseEvent me) {
		// TODO Auto-generated method stub
		super.mousePressed(me);
		c.mousePressed(me);
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		// TODO Auto-generated method stub
		super.mouseReleased(me);
		c.mouseReleased(me);
	}


	
	@Override
	public void keyPressed(KeyEvent ke) {
		// TODO Auto-generated method stub
		//////////////////A 키 눌림///////////////////////////////
		if(ke.getKeyCode() == ke.VK_A ){
		
			/////////////중복 허용 안함//////////////
			if(!IS_A_DOWN){
				if(sk1_check==1){
					sk1.add(c.matchSkill(1));
					sk1.lastElement().activate();
					sk1.lastElement().keyPressed(ke);
				}
			}
		}
		//////////////////////////////////////////////////////
		
		//////////////////ESCAPE 키 눌림///////////////////////////
		if(ke.getKeyCode() == ke.VK_ESCAPE){

			//////////////중복 허용 안함/////////////////
			if(!IS_ESCAPE_DOWN){
				if(phase != MENUPHASE){
					menu.popUp();
					phase = MENUPHASE;
				}
				else if(phase == MENUPHASE){
					menu.unPop();
					phase = SCENEPHASE;
				}
			}
		}
		/////////////////////////////////////////////////////////
		
		//////////////////ENTER 키 눌림//////////////////////////
		if(ke.getKeyCode() == ke.VK_ENTER && IS_ENTER_DOWN == false){
			

		}
		//////////////////////////////////////////////////////
		
		/////////////////키 플레그 처리////////////////////////
		super.keyPressed(ke);
		///////////////////////////////////////////////////

		
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		// TODO Auto-generated method stub
		if(ke.getKeyCode() == ke.VK_A && !sk1.isEmpty())
			sk1.lastElement().keyReleased(ke);
		super.keyReleased(ke);
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		super.keyTyped(arg0);
	}
	
	//////////////////////////////////////////////////////////////////////////

}
