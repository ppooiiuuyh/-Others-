package gui_components.panels;

import gui_components.smallComponents.JPanel_TestTab_SoundTest;
import data.Layouts;
import main.Manager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * Created by ppooi on 2017-03-26.
 */
public class JPanel_TestTab_FirstPanel extends JPanel {

    /*Upper Components*/
    JPanel_TestTab jPanel_testTab;
    Manager manager;

    /*Components*/
    JPanel_TestTab_SoundTest jPanel_testTab_soundTest;

    public JPanel_TestTab_FirstPanel(JPanel_TestTab jPanel_testTab){
        init_UpperComponents(jPanel_testTab);
        init_Components();
        init_Locals();
        init_This();
    }

    public void init_UpperComponents(JPanel_TestTab jPanel_testTab){
        this.jPanel_testTab = jPanel_testTab;
        this.manager = jPanel_testTab.getManager();
    }
    public void init_Components(){
        jPanel_testTab_soundTest = new JPanel_TestTab_SoundTest(this);
        this.add(jPanel_testTab_soundTest);
    }
    public void init_Locals(){

    }
    public void init_This(){

        TitledBorder border = BorderFactory.createTitledBorder("Sound Test");
        border.setTitleColor(Color.black);
        this.setBorder(border);
        this.setLayout(null);
        this.setBounds(Layouts.TestTab_FirstPanel_Bounds);

//        MyUtillities.showEffectiveArea(Layouts.MainTab_FirstPanel_PanelBounds,this);

    }


    public JPanel_TestTab getUpperComponents(){
        return jPanel_testTab;
    }
    public Manager getManager(){
        return manager;
    }


}
