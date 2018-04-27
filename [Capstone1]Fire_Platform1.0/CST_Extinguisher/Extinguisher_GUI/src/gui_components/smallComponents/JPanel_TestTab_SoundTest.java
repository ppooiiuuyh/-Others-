package gui_components.smallComponents;

import accesory.Interface_Components;
import gui_components.panels.JPanel_TestTab_FirstPanel;
import data.Layouts;
import main.Manager;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Created by ppooi on 2017-03-27.
 */
public class JPanel_TestTab_SoundTest extends JPanel implements Interface_Components,ActionListener{

    /*Upper Components*/
    JPanel_TestTab_FirstPanel jPanel_testTab_firstPanel;
    Manager manager;

    /*Components*/
    JLabel jLabel;
    JComboBox jComboBox;
    JButton jButton;


    /*Local variables*/
    URL fileName;
    Clip clip;

    public JPanel_TestTab_SoundTest(JPanel_TestTab_FirstPanel jPanel_testTab_firstPanel) {
        init_UpperComponents(jPanel_testTab_firstPanel);
        init_Comps();
        init_Locals();
        init_This();
    }

    public void init_UpperComponents(JPanel_TestTab_FirstPanel jPanel_testTab_firstPanel){
        this.jPanel_testTab_firstPanel = jPanel_testTab_firstPanel;
        this.manager = jPanel_testTab_firstPanel.getManager();
    }

    @Override
    public void init_UpperComponents() {

    }

    @Override
    public void init_Comps() {

        /*JLabel*/
        int labelX = 0;
        int labelY = 0;
        int labelSizeX = (int)Layouts.SoundTest_SizeX/4;
        int labelSizeY = (int)Layouts.SoundTest_SizeY;
        jLabel = new JLabel("Alarm Sound");
        jLabel.setLocation(labelX,labelY);
        jLabel.setSize(labelSizeX,labelSizeY);

        EtchedBorder border = (EtchedBorder) BorderFactory.createEtchedBorder();
        jLabel.setBorder(border);
//        jLabel.setOpaque(true);
//        jLabel.setBackground(Color.white);
        jLabel.setFont(new Font("고딕", Font.BOLD, 15));
        jLabel.setHorizontalAlignment(JLabel.CENTER);
        jLabel.setVerticalAlignment(JLabel.CENTER);

        this.add(jLabel);




        /*JCombobox*/
        int comboX = labelSizeX;
        int comboY = 0;
        int comboSizeX = (int)Layouts.SoundTest_SizeX/4*2;
        int comboSizeY = (int)Layouts.SoundTest_SizeY;

        jComboBox = new JComboBox();
        jComboBox.setLocation(comboX,comboY);
        jComboBox.setSize(comboSizeX,comboSizeY);
        jComboBox.addItem(new ComboItem_Sound("lobby.wav"));
        jComboBox.addItem(new ComboItem_Sound("siren.wav"));
        jComboBox.addItem(new ComboItem_Sound("alarm3"));
        this.add(jComboBox);



        /*JButton*/
        int buttonX = labelSizeX + comboSizeX;
        int buttonY = 0;
        int buttonSizeX = (int)Layouts.SoundTest_SizeX/4*1;
        int buttonSizeY = (int)Layouts.SoundTest_SizeY;

        jButton = new JButton("Play");
        jButton.setLocation(buttonX,buttonY);
        jButton.setSize(buttonSizeX,buttonSizeY);
        jButton.addActionListener(this);
        this.add(jButton);





    }

    @Override
    public void init_Locals() {
        fileName = getClass().getClassLoader().getResource("lobby.wav");
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void init_This() {
        this.setBounds(Layouts.SoundTest_Bounds);
        this.setLayout(null);
    }




    public void play(){
        try
        {
            AudioInputStream ais = AudioSystem.getAudioInputStream(fileName);

            clip.open(ais);
            System.out.println(clip.getMicrosecondLength());
            clip.start();
            clip.loop(0);

                System.out.println("play");


        }
        catch(Exception e)
        {

        }
    }
    public void soundStop(){
        clip.stop();
        clip.close();
    }


    @Override
    public JPanel_TestTab_FirstPanel getUpperComponent() {
        return jPanel_testTab_firstPanel;
    }

    @Override
    public Manager getManager() {
        return manager;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == jButton) {
            if (jButton.getText().equals("Play")) {
                fileName = getClass().getClassLoader().getResource(jComboBox.getSelectedItem().toString());
                play();
                jButton.setText("Stop");

            } else if (jButton.getText().equals("Stop")) {
                soundStop();
                jButton.setText("Play");
            }
        }
    }
}
