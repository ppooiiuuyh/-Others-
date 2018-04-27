package gui_components.smallComponents;

import accesory.Interface_Components;
import gui_components.panels.JPanel_MainTab_SecondPanel;
import data.Layouts;
import main.Manager;

import javax.swing.*;

/**
 * Created by ppooi on 2017-03-27.
 */
public class JPanel_MainTab_ConnetionCheck extends JPanel implements Interface_Components {
    /*Upper Components*/
    JPanel_MainTab_SecondPanel jPanel_mainTab_secondPanel;
    Manager manager;

    /*Components*/
    JTextArea jLabel;
    /*Local Variables*/


    public JPanel_MainTab_ConnetionCheck(JPanel_MainTab_SecondPanel jPanel_mainTab_secondPanel){
        init_UpperComponents(jPanel_mainTab_secondPanel);
        init_Comps();
        init_Locals();
        init_This();
    }



    public void init_UpperComponents(JPanel_MainTab_SecondPanel jPanel_mainTab_secondPanel){
        this.jPanel_mainTab_secondPanel = jPanel_mainTab_secondPanel;
        this.manager = manager;
    }
    @Override
    public void init_UpperComponents() {

    }

    @Override
    public void init_Comps() {

        jLabel = new ConnectionCheck_TextArea(this);
        this.add(jLabel);
    }

    @Override
    public void init_Locals() {

    }

    @Override
    public void init_This() {
        this.setBounds(Layouts.ConnectionCheck_Bounds);
        this.setLayout(null);
    }

    @Override
    public JPanel_MainTab_SecondPanel getUpperComponent() {
        return jPanel_mainTab_secondPanel;
    }

    @Override
    public Manager getManager() {
        return manager;
    }
}
