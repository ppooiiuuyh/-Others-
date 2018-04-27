package gui_components.panels;

import gui_components.smallComponents.JPanel_MainTab_ConnetionCheck;
import data.Layouts;
import main.Manager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by ppooi on 2017-03-26.
 */
public class JPanel_MainTab_SecondPanel extends JPanel {

    /*Upper Components*/
    JPanel_MainTab jPanel_mainTab;
    Manager manager;

    /*Components*/
    JPanel_MainTab_ConnetionCheck jPanel_mainTab_connetionCheck;

    public JPanel_MainTab_SecondPanel(JPanel_MainTab jPanel_mainTab){
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
        jPanel_mainTab_connetionCheck = new JPanel_MainTab_ConnetionCheck(this);
        this.add(jPanel_mainTab_connetionCheck);
    }
    public void init_Locals(){

    }
    public void init_This(){

		TitledBorder border = BorderFactory.createTitledBorder("Wifi Status");
		border.setTitleColor(Color.black);
		this.setBorder(border);
		this.setLayout(null);
		this.setLocation(Layouts.MainTab_SecondPanel_LocationX,Layouts.MainTab_SecondPanel_LocationY);
		this.setSize((int)Layouts.MainTab_SecondPanel_SizeX,(int)Layouts.MainTab_SecondPanel_SizeY);

//        MyUtillities.showEffectiveArea(Layouts.MainTab_SecondPanel_PanelBounds,this);

    }




    public JPanel_MainTab getUpperComponents(){
        return jPanel_mainTab;
    }
    public Manager getManager(){
        return manager;
    }


}
