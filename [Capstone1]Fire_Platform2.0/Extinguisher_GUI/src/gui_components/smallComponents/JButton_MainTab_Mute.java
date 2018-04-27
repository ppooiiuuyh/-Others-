package gui_components.smallComponents;

import main.Manager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ppooi on 2017-04-03.
 */
public class JButton_MainTab_Mute extends JButton implements ActionListener {

    /*Upper components*/
    JPanel_MainTab_Sound jPanel_mainTab_sound;
    Manager manager;

    public JButton_MainTab_Mute(JPanel_MainTab_Sound jPanel_mainTab_sound){
        init_Upper(jPanel_mainTab_sound);
        init_This();
    }

    public void init_Upper(JPanel_MainTab_Sound jPanel_mainTab_sound){
        this.jPanel_mainTab_sound = jPanel_mainTab_sound;
        this.manager = jPanel_mainTab_sound.getManager();

    }

    public void init_This(){
        this.setText("Mute");
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(((JButton_MainTab_Mute)e.getSource()).getText().equals("Mute")){
          manager.getSounder().mute();
          this.setText("DeMute");
        }
        else if((((JButton_MainTab_Mute)e.getSource()).getText().equals("DeMute"))){
           manager.getSounder().demute();
           this.setText("Mute");
       }
    }
}
