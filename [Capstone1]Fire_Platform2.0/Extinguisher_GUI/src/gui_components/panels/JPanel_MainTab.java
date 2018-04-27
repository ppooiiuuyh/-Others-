package gui_components.panels;

import main.Manager;

import javax.swing.*;

/**
 * Created by ppooi on 2017-03-26.
 */
public class JPanel_MainTab extends JPanel {

    /*Upper Components*/
    JTabbedPanel_Main jTabbedPanel_main;
    Manager manager;

    /*Components*/
    JPanel_MainTab_FirstPanel jPanel_mainTab_firstPanel;
    JPanel_MainTab_SecondPanel jPanel_mainTab_secondPanel;
    JPanel_MainTab_ThirdPanel jPanel_mainTab_ThirdPanel;

    public JPanel_MainTab(JTabbedPanel_Main jTabbedPanel_main){
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
        jPanel_mainTab_firstPanel = new JPanel_MainTab_FirstPanel(this);
        this.add(jPanel_mainTab_firstPanel);

        jPanel_mainTab_secondPanel = new JPanel_MainTab_SecondPanel(this);
        this.add(jPanel_mainTab_secondPanel);

        jPanel_mainTab_ThirdPanel = new JPanel_MainTab_ThirdPanel(this);
        this.add(jPanel_mainTab_ThirdPanel);
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
