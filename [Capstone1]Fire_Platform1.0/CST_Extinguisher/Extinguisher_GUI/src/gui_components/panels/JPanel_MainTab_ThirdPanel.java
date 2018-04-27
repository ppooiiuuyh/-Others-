package gui_components.panels;

import data.Layouts;
import gui_components.smallComponents.JPanel_MainTab_ConnetionCheck;
import gui_components.smallComponents.JPanel_MainTab_Sound;
import main.Manager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by ppooi on 2017-03-26.
 */
public class JPanel_MainTab_ThirdPanel extends JPanel {

    /*Upper Components*/
    JPanel_MainTab jPanel_mainTab;
    Manager manager;

    /*Components*/
    JPanel_MainTab_Sound jPanel_mainTab_sound;

    public JPanel_MainTab_ThirdPanel(JPanel_MainTab jPanel_mainTab){
        init_UpperComponents(jPanel_mainTab);
        init_Components();
        init_Locals();
        init_This();
    }

    public void init_UpperComponents(JPanel_MainTab jPanel_mainTab){
        this.jPanel_mainTab = jPanel_mainTab;
        this.manager = jPanel_mainTab.getManager();
    }
    public void init_Components(){
        jPanel_mainTab_sound = new  JPanel_MainTab_Sound(this);
        this.add(jPanel_mainTab_sound);
    }
    public void init_Locals(){

    }
    public void init_This(){

		TitledBorder border = BorderFactory.createTitledBorder("Sound");
		border.setTitleColor(Color.black);
		this.setBorder(border);
		this.setLayout(null);
		this.setLocation(Layouts.MainTab_ThirdPanel_LocationX,Layouts.MainTab_ThirdPanel_LocationY);
		this.setSize((int)Layouts.MainTab_ThirdPanel_SizeX,(int)Layouts.MainTab_ThirdPanel_SizeY);

//        MyUtillities.showEffectiveArea(Layouts.MainTab_ThirdPanel_PanelBounds,this);

    }




    public JPanel_MainTab getUpperComponents(){
        return jPanel_mainTab;
    }
    public Manager getManager(){
        return manager;
    }


}
