package subClasses;

import gameScene.GameScene;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import data.Constants;


public class Effect extends JPanel {
	
	///////////////////////////////데이터//////////////////////////////////////////
	Constants constants;
	GameScene gc;
	GameObject go;
	///////////////////////////process flags/////////////////////////////////
	boolean IS_DELETEABLE;
	boolean IS_CREATEABLE;
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
	
	////////////////////////////////////////////////////////////////////////////////
	
	/////////////////////////////////메서드//////////////////////////////////////////////////
	/////////////////////////////////construct////////////////////////////////////////////
	public Effect()
	{
	}
	public Effect(GameScene gameScene)
	{
		gc = gameScene;
	}
	public Effect(GameObject gameObject)
	{
		go = gameObject;
	}
	
	public void init()
	{
		constants = new Constants();

		this.setVisible(false);
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
	}
	////////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////process control////////////////////////////////
	public void activate()
	{
		
	}
	public void deactivate()
	{
		
	}
	////////////////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////is, get, set values///////////////////////////////////	
	public boolean isCharged(){
		return IS_CHARGED;
	}
	public boolean isDeleteable(){
		return IS_DELETEABLE;
	}
	public boolean isCreateable(){
		return IS_CREATEABLE;
	}
	public boolean isProcessStop(){
		return IS_PROCESSSTOP;
	}
	public boolean isUse(){
		return IS_USE;
	}
	public int getCnt(){
		return cnt;
	}
	public int getPhase(){
		return phase;
	}
	public double getPosx(){
		return posx;
	}
	public double getPosy(){
		return posy;
	}
	public void setSizeScale(double n){
		sizeScale = n;
	}
	public void setImg(Image img){
		
	}
	public void setPos(double x, double y){
		posx=x;
		posy=y;
	}
	public void setPhase(int n){
		phase = n;
	}
	public void setDeleteable(boolean b){
		IS_DELETEABLE = b;
	}
	public void setCreateable(boolean b){
		IS_CREATEABLE = b;
	}
	public void setProcessStop(boolean b){
		IS_PROCESSSTOP = b;
	}
	////////////////////////////////////////////////////////////////////////
	
	/////////////////paint, process, event/////////////////////////////////
	public void paint(Graphics g){
		
	}
	public void paint(Image img){
		
	}
	
	public void process(){
		
	}
	
	public void mouseCliked(MouseEvent me){
		
	}
	public void mousePressed(MouseEvent me){
		
	}
	public void keyPressed(KeyEvent ke){
	
	}
	public void keyReleased(KeyEvent ke){
		
	}
	///////////////////////////////////////////////////////////////////////
	
	//////////////////////////////////////////////////////////////////////////

}
