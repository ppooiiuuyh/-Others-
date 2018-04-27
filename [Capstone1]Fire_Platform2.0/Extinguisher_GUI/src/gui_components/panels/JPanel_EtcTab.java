package gui_components.panels;

import main.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ppooi on 2017-03-26.
 */
public class JPanel_EtcTab extends JPanel implements ActionListener{

    /*Upper Components*/
    JTabbedPanel_Main jTabbedPanel_main;
    Manager manager;

    /*Components*/
    JButton jButton;


    public JPanel_EtcTab(JTabbedPanel_Main jTabbedPanel_main){
        init_UpperComponents(jTabbedPanel_main);
        init_Comps();
        init_Locals();
        init_This();
    }

    public void init_UpperComponents(JTabbedPanel_Main jTabbedPanel_main){
        this.jTabbedPanel_main = jTabbedPanel_main;
        this.manager = jTabbedPanel_main.getManager();
    }
    public void init_Locals(){

    }
    public void init_Comps(){
        jButton = new JButton("close");
        jButton.addActionListener(this);
        this.add(jButton);
    }
    public void init_This(){
        this.setLayout(new GridLayout(4,4));
    }

    public JTabbedPanel_Main getUpperComponent(){
        return jTabbedPanel_main;
    }
    public Manager getManager(){
        return manager;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==jButton){
            System.exit(0);
        }
    }
}
