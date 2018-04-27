package gui_components.frames;

import gui_components.panels.JTabbedPanel_Main;
import data.Layouts;
import main.Manager;

import javax.swing.*;


/**
 * Created by ppooi on 2017-03-26.
 */
public class MainFrame extends JFrame{

    /*Upper gui_components*/
    Manager manager;


    /*Components*/
    JTabbedPanel_Main jTabbedPanel_main;

    public MainFrame(Manager manager){
        init_this();
        init_UpperComponents(manager);
        init_Components();
        init_Locals();
    }


    public void init_UpperComponents(Manager manager){
        this.manager = manager;
    }
    public void init_Components(){
        jTabbedPanel_main = new JTabbedPanel_Main(this);
        this.add(jTabbedPanel_main);
    }
    public void init_Locals(){

    }
    public void init_this(){
//        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.setUndecorated(true);

//        GridLayout grid = new GridLayout(1,1);
        this.setLayout(null);
        this.setLocation(Layouts.MainFrame_LocationX,Layouts.MainFrame_LocationY);
        this.setSize((int)Layouts.MainFrame_SizeX,(int)Layouts.MainFrame_SizeY);
        this.setTitle("ExtinguisherGUI");
        //this.setExtendedState(this.getExtendedState()|JFrame.MAXIMIZED_BOTH);
        this.setResizable(false);
        this.setVisible(true);



    }

    public Manager getManager(){
        return manager;
    }



}
