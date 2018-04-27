package main;

import gameScene.BattleScene;
import gameScene.BattleScene;
import gameScene.GameScene;
import gameScene.LobbyScene;
import gameSystem.Menu_BattleScene;

import data.GameImages;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.geom.AffineTransform;
import java.sql.Time;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.*;

import subClasses.BackGround;
import subClasses.Character;
import subClasses.Effect;
import subClasses.Skill;
import subClasses.Skill1;
import subClasses.Sound;
import data.Constants;

public class GameManager extends JFrame implements  MouseListener, WindowListener, KeyListener{

	////////////////////////////////데이터/////////////////////////////////
	Constants constants;
	GameImages gameImages;
	GameScene nowScene;
	
	//////////////////////////////////////////////////////////////////
	
	/////////////////////////메서드///////////////////////////////////
	/////////////////////construct///////////////////////////////////
	public GameManager(){
		super();
		init();	
	}
	public void init(){
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,Toolkit.getDefaultToolkit().getScreenSize().height);
		this.setLayout(null);
		this.setUndecorated(true);
		this.setVisible(true);
		this.addMouseListener(this);
		this.addWindowListener(this);
		this.addKeyListener(this);

		constants = new Constants();
		gameImages = new GameImages();
		nowScene = new GameScene(this);

		chaingeScene(new LobbyScene(this));
		
	}
	///////////////////////////////////////////////////////////////////////////
	
	//////////////////////is, get, set/////////////////////////////////////////
	public GameImages getImgBox(){
		return gameImages;
	}
	
	public void	chaingeScene(GameScene gc)
	{
		nowScene.deactivate();
		nowScene = gc;
		nowScene.activate();
	}
	//////////////////////////////////////////////////////////////////////////
	
	/////////////////////paint, process ,event////////////////////////////////////////
	public void exitProgram(){
		saveGame();
		System.exit(0);
	}
	public void saveGame(){
		
	}
	
	public void process(){
			
	}
	
	@Override
	public void mouseClicked(MouseEvent me) {
		// TODO Auto-generated method stub
		nowScene.mouseClicked(me);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent me) {
		// TODO Auto-generated method stub
		nowScene.mousePressed(me);
		if(me.getButton() == me.BUTTON1)
		{
		
		}
	}

	@Override
	public void mouseReleased(MouseEvent me) {
		// TODO Auto-generated method stub
		nowScene.mouseReleased(me);
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.exit(1);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		// TODO Auto-generated method stub
		nowScene.keyPressed(ke);
		
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		// TODO Auto-generated method stub
		nowScene.keyReleased(ke);

		
	}
	

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	/////////////////////////////////////////////////////////////
}
