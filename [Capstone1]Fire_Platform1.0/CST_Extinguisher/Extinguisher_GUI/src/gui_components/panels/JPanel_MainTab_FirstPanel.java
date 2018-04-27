package gui_components.panels;

import gui_components.smallComponents.JPanel_MainTab_FirePicture;
import data.Layouts;
import main.Manager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by ppooi on 2017-03-26.
 */
public class JPanel_MainTab_FirstPanel extends JPanel {

    /*Upper Components*/
    JPanel_MainTab jPanel_mainTab;
    Manager manager;

    /*Components*/
    JPanel_MainTab_FirePicture jPanel_mainTab_firePicture;

    public JPanel_MainTab_FirstPanel(JPanel_MainTab jPanel_mainTab){
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
        jPanel_mainTab_firePicture = new JPanel_MainTab_FirePicture(this);
        this.add(jPanel_mainTab_firePicture);
    }
    public void init_Locals(){

    }
    public void init_This(){

		TitledBorder border = BorderFactory.createTitledBorder("Image");
		border.setTitleColor(Color.black);
		this.setBorder(border);
		this.setLayout(null);
		this.setSize((int)Layouts.MainTab_FirstPanel_SizeX,(int)Layouts.MainTab_FirstPanel_SizeY);

//        MyUtillities.showEffectiveArea(Layouts.MainTab_FirstPanel_PanelBounds,this);

    }


    public JPanel_MainTab getUpperComponents(){
        return jPanel_mainTab;
    }
    public Manager getManager(){
        return manager;
    }


}
