package gui_components.panels;

import accesory.Interface_Components;
import gui_components.smallComponents.JTable_ConnectionTab_ConnectionState;
import data.Layouts;
import main.Manager;

import javax.swing.*;

/**
 * Created by ppooi on 2017-03-27.
 */
public class JPanel_ConnectionTab extends JPanel implements Interface_Components{

    /*Upper Components*/
    JTabbedPanel_Main jTabbedPanel_main;
    Manager manager;

    /*Components*/
    JScrollPane jScrollPane;
    JTable_ConnectionTab_ConnectionState jTable_connectionTab_connectionState;



    public JPanel_ConnectionTab(JTabbedPanel_Main jTabbedPanel_main){
        init_UpperComponents(jTabbedPanel_main);
        init_Comps();
        init_Locals();
        init_This();
    }



    public void init_UpperComponents(JTabbedPanel_Main jTabbedPanel_main){
        this.jTabbedPanel_main = jTabbedPanel_main;
        this.manager = jTabbedPanel_main.getManager();
    }
    @Override
    public void init_UpperComponents() {

    }

    @Override
    public void init_Comps() {
        jTable_connectionTab_connectionState = new JTable_ConnectionTab_ConnectionState(this);
        jScrollPane = new JScrollPane(jTable_connectionTab_connectionState);
        jScrollPane.setBounds(Layouts.ConnectionState_Bounds);
        this.add(jScrollPane);
    }

    @Override
    public void init_Locals() {

    }

    @Override
    public void init_This() {
        this.setBounds(Layouts.ConnectionTab_Bounds);
        this.setLayout(null);
    }

    @Override
    public JTabbedPanel_Main getUpperComponent() {
        return jTabbedPanel_main;
    }

    @Override
    public Manager getManager() {
        return manager;
    }
}
