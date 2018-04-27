package gui_components.panels;

import gui_components.frames.MainFrame;
import data.Layouts;
import main.Manager;

import javax.swing.*;

/**
 * Created by ppooi on 2017-03-26.
 */
public class JTabbedPanel_Main extends JTabbedPane {

    /*Upper Components*/
    Manager manager;
    MainFrame mainFrame;

    /*Components*/
    JPanel_MainTab jPanel_MainTab;
    JPanel_ConnectionTab jPanel_connetionTab;
    JPanel_ModeTab jPanel_modeTab;
    JPanel_TestTab jPanel_testTab;
    JPanel_EtcTab jPanel_EtcTab;

    public JTabbedPanel_Main(MainFrame mainFrame){
        init_UpperComponents(mainFrame);
        init_Components();
        init_This();
    }

    public void init_UpperComponents(MainFrame mainFrame){
        this.manager = mainFrame.getManager();
        this.mainFrame = mainFrame;
    }
    public void init_Components(){
        jPanel_MainTab = new JPanel_MainTab(this);
        this.addTab("Main",jPanel_MainTab);

        jPanel_connetionTab = new JPanel_ConnectionTab(this);
        this.addTab("Connection",jPanel_connetionTab);

//        jPanel_modeTab = new JPanel_ModeTab(this);
//        this.addTab("Mode",jPanel_modeTab);

        jPanel_testTab = new JPanel_TestTab(this);
        this.addTab("Test",jPanel_testTab);


        jPanel_EtcTab = new JPanel_EtcTab(this);
        this.addTab("Etc",jPanel_EtcTab);
    }
    public void init_This(){
        this.setSize((int)Layouts.MainTab_SizeX,(int)Layouts.MainTab_SizeY);
        this.setVisible(true);
    }


    public Manager getManager(){
        return manager;
    }
    public MainFrame getUpperComponents(){
        return mainFrame;
    }


}
