package gui_components.smallComponents;

import accesory.Interface_Components;
import data.Layouts;
import gui_components.panels.JPanel_MainTab_ThirdPanel;
import gui_components.panels.JPanel_MainTab_ThirdPanel;
import main.Manager;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ppooi on 2017-03-27.
 */
public class JPanel_MainTab_Sound extends JPanel implements Interface_Components {

    /*Upper Components*/
    JPanel_MainTab_ThirdPanel jPanel_mainTab_ThirdPanel;
    Manager manager;
    /*Components*/
    JButton_MainTab_StopSound jButton_mainTab_stopSound;
    JButton_MainTab_Mute jButton_mainTab_Mute;
    /*Local variables*/


    public JPanel_MainTab_Sound(JPanel_MainTab_ThirdPanel jPanel_mainTab_ThirdPanel) {
        init_UpperComponents(jPanel_mainTab_ThirdPanel);
        init_Comps();
        init_Locals();
        init_This();

    }

    public void init_UpperComponents(JPanel_MainTab_ThirdPanel jPanel_mainTab_ThirdPanel){
        this.jPanel_mainTab_ThirdPanel = jPanel_mainTab_ThirdPanel;
        manager = jPanel_mainTab_ThirdPanel.getManager();
    }

    @Override
    public void init_UpperComponents() {

    }

    @Override
    public void init_Comps() {
        jButton_mainTab_stopSound = new JButton_MainTab_StopSound(this);
        this.add(jButton_mainTab_stopSound);

        jButton_mainTab_Mute = new JButton_MainTab_Mute(this);
        this.add(jButton_mainTab_Mute);
    }

    @Override
    public void init_Locals() {
    }

    @Override
    public void init_This() {
        this.setBounds(Layouts.MainTab_ThirdPanel_PanelBounds);
        this.setLayout(new GridLayout(2,1));
    }




    @Override
    public JPanel_MainTab_ThirdPanel getUpperComponent() {
        return jPanel_mainTab_ThirdPanel;
    }

    @Override
    public Manager getManager() {
        return manager;
    }
}
