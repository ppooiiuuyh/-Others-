package data;

import java.awt.Image;
import java.awt.Toolkit;

import subClasses.MagicCircleEffect;
import subClasses.MagicCircleGrowth;
import subClasses.MagicCircleStatic;

public class GameImages {
	
	///////////////////interface_HP////////
	public Image img_hp = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("interface_hp.png"));
	public Image img_hpglass = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("interface_hpglass5.png"));
	public Image img_bar = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("interface_bar.png"));
	
	
	/////////////////////interface_Lobby////////////
	public Image icon_itemActive = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("icon_item.png"));
	public Image icon_itemDeactive = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("icon_item.png"));
	public Image icon_item = icon_itemActive;

	public Image icon_skillActive = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("icon_skill.png"));
	public Image icon_skillDeactive = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("icon_skill.png"));
	public Image icon_skill = icon_skillActive;

	public Image icon_charActive = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("icon_character.png"));
	public Image icon_charDeactive = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("icon_character.png"));
	public Image icon_char = icon_charActive;

	public Image icon_dungeonActive = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("icon_dungeonActive.png"));
	public Image icon_dungeonDeactive = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("icon_dungeon.png"));
	public Image icon_dungeon = icon_dungeonActive;
	
	///////////////////////interface status////////////////////
	public Image statusFrame = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("interface_statusframe.png"));
	
	
	
	//////////menu battleScene////////////////////////
	public Image menu_battleScene_glass = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("redglass3per.png"));
	
	public Image returnToGameDeactive = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("returntogame.png"));
	public Image returnToGameActive =Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("returntogameactive.png"));
	public Image returnToGame = returnToGameDeactive;

	public Image returnToLobbyDeactive = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("returntolobby.png"));
	public Image returnToLobbyActive =Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("returntolobbyactive.png"));
	public Image returnToLobby = returnToLobbyDeactive;
	
	public Image exitProgramDeactive = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("exitprogram.png"));
	public Image exitProgramActive =Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("exitprogramactive.png"));
	public Image exitProgram = exitProgramDeactive;
	
	/////////////////////////////////menu lobbyscene////////////////
	public Image glass = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("redglass3per.png"));
	
	public Image returnToTitleDeactive = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("returntotitle.png"));
	public Image returnToTitleActive =Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("returntotitleactive.png"));
	public Image returnToTitle = returnToTitleDeactive;
	
	
	//////////////////////////////skill1_magicircle/////////////
	public Image skill1_mcg = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("magiccircleuncharged.png"));
	public Image skill1_mce = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("magiccircleuncharged.png"));
	public Image skill1_mcs = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("magiccircle.png"));

	public Image skill1_mcg2 = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("magiccircle2uncharged.png"));
	public Image skill1_mce2 = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("magiccircle2uncharged.png"));
	public Image skill1_mcs2 = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("magiccircle2.png"));

	public Image skill1_mcg3 = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("magiccircleuncharged.png"));
	public Image skill1_mce3 = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("magiccirclefull.png"));
	public Image skill1_mcs3 = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("magiccirclecyan.png"));

	////////////////////////////////skill1_magiccircle2///////////////////////////////
	public Image skill1_mc2_mcs = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("magiccircle.png"));
	
	
	///////////////////////////////character////////////////////////////
	public Image[] char_imgStore = new Image[] { Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("char_2.png")),
		     Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("char_3.png")),
		     Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("char_4.png")),
		    // Toolkit.getDefaultToolkit().getImage("char_4.png"),
			     Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("char4.png"))};

	public Image char_img = Toolkit.getDefaultToolkit().getImage("char4.png");

	//////////////////////////////thunder//////////////////////////
	public Image[] thunder_imgStore = new Image[] {
										Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("tc0.png")),
									    Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("tc1.png")),
									    Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("tc2.png")),
									    Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("tc3.png")),
										Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("tc4.png")),
										Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("tc5.png")),
										Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("tc6.png")),
										Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("tc7.png")),
										Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("tc8.png"))};

	public Image thunder_img = Toolkit.getDefaultToolkit().getImage("");

	
	
	
	
	
	
	
	
	
	
	public GameImages(){
		
	}
}
