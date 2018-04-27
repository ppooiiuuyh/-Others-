package gui_components.panels;

import main.Manager;

import javax.swing.*;

/**
 * Created by ppooi on 2017-03-26.
 */
public class JPanel_ModeTab extends JPanel {

    /*Upper Components*/
    JTabbedPanel_Main jTabbedPanel_main;
    Manager manager;

    /*Components*/
    JPanel_ModeTab_FirstPanel jPanel_modeTab_firstPanel;


    public JPanel_ModeTab(JTabbedPanel_Main jTabbedPanel_main){
        init_This();
        init_UpperComponents(jTabbedPanel_main);
        init_Comps();
        init_Locals();
    }

    public void init_UpperComponents(JTabbedPanel_Main jTabbedPanel_main){
        this.jTabbedPanel_main = jTabbedPanel_main;
        this.manager = jTabbedPanel_main.getManager();
    }
    public void init_Locals(){

    }
    public void init_Comps(){
        jPanel_modeTab_firstPanel = new JPanel_ModeTab_FirstPanel(this);
        this.add(jPanel_modeTab_firstPanel);
    }

    public void init_This(){
        this.setLayout(null);
        this.setVisible(true);
    }

    public JTabbedPanel_Main getUpperComponent(){
        return jTabbedPanel_main;
    }



    public Manager getManager(){
        return manager;
    }



}
