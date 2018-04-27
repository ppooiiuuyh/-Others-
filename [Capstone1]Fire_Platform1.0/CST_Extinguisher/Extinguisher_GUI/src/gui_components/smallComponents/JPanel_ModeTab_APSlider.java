package gui_components.smallComponents;

import accesory.Interface_Components;
import accesory.MyUtillities;
import gui_components.panels.JPanel_ModeTab_FirstPanel;
import data.Layouts;
import main.Manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

/**
 * Created by ppooi on 2017-03-27.
 */
public class JPanel_ModeTab_APSlider extends JPanel implements Interface_Components, MouseListener ,MouseMotionListener{

    /*Upper Components*/
    JPanel_ModeTab_FirstPanel jPanel_modeTab_firstPanel;

    /*Components*/


    /*Local variables*/
    int mode;

    double knob_LocationX_Now;
    double knob_LocationY_Now;
    Rectangle box_APArea;
    Rectangle box_hostArea;

    public JPanel_ModeTab_APSlider(JPanel_ModeTab_FirstPanel jPanel_modeTab_firstPanel){
        init_UpperComponents(jPanel_modeTab_firstPanel);
        init_Comps();
        init_Locals();
        init_This();
    }





    public void init_UpperComponents(JPanel_ModeTab_FirstPanel jPanel_modeTab_firstPanel){
        this.jPanel_modeTab_firstPanel = jPanel_modeTab_firstPanel;
    }

    @Override
    public void init_UpperComponents() {

    }

    @Override
    public void init_Comps() {

    }

    @Override
    public void init_Locals() {
        mode = 3;
        if(mode == 0) {
            knob_LocationX_Now = Layouts.SliderKnob_LocationX_AP;
            knob_LocationY_Now = Layouts.SliderKnob_LocationY_AP;
        }
        else if(mode == 1){
            knob_LocationX_Now = Layouts.SliderKnob_LocationX_Host;
            knob_LocationY_Now = Layouts.SliderKnob_LocationY_Host;
        }
        else {
            knob_LocationX_Now = Layouts.SliderKnob_LocationX_Default;
            knob_LocationY_Now = Layouts.SliderKnob_LocationY_Default;
        }
        box_APArea = new Rectangle(Layouts.SliderKnob_LocationX_AP,Layouts.SliderKnob_LocationY_AP,Layouts.SliderKnob_SizeX,Layouts.SliderKnob_SizeY);
        box_hostArea = new Rectangle(Layouts.SliderKnob_LocationX_Host,Layouts.SliderKnob_LocationY_Host,Layouts.SliderKnob_SizeX,Layouts.SliderKnob_SizeY);

    }

    @Override
    public void init_This() {
        this.setBounds(Layouts.ModeTab_APSlider_Bounds);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Image sliderBG = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("slider2.png"));
        g.drawImage(sliderBG,Layouts.SliderBG_LocationX,Layouts.SliderBG_LocationY,Layouts.SliderBG_SizeX,Layouts.SliderBG_SizeY,null);


     /*   Image sliderKnob = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("knob4.png"));
        g.drawImage(sliderKnob,Layouts.SliderKnob_LocationX_AP,Layouts.SliderKnob_LocationY_AP,Layouts.SliderKnob_SizeX,Layouts.SliderKnob_SizeY,null);
     */
        Image sliderKnob = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("knob4.png"));
        g.drawImage(sliderKnob,(int)knob_LocationX_Now,(int)knob_LocationY_Now,Layouts.SliderKnob_SizeX,Layouts.SliderKnob_SizeY,null);

        this.repaint();

    }






    @Override
    public JComponent getUpperComponent() {
        return null;
    }

    @Override
    public Manager getManager() {
        return null;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        PointerInfo ms = MouseInfo.getPointerInfo();
        int msX = ms.getLocation().x;
        int msY = ms.getLocation().y;

        if(MyUtillities.isInRect(box_APArea,msX,msY)){
            if(mode != 0){
                mode = 0;
                knob_LocationX_Now = Layouts.SliderKnob_LocationX_AP;
                knob_LocationY_Now = Layouts.SliderKnob_LocationY_AP;
                this.repaint();

                Runtime runtime = Runtime.getRuntime();
                Process process = null;
                try {
                    process = runtime.exec("/home/pi/Desktop/runAPmode");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    process.waitFor();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }


            }
        }
        else if(MyUtillities.isInRect(box_hostArea,msX,msY)){
            if(mode != 1) {
                mode = 1;
                knob_LocationX_Now = Layouts.SliderKnob_LocationX_Host;
                knob_LocationY_Now = Layouts.SliderKnob_LocationY_Host;
                this.repaint();

                Runtime runtime = Runtime.getRuntime();
                Process process = null;
                try {
                    process = runtime.exec("/home/pi/Desktop/runCLmode");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                try {
                    process.waitFor();
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
        else{
            if(mode == 0){
                mode = 0;
                knob_LocationX_Now = Layouts.SliderKnob_LocationX_AP;
                knob_LocationY_Now = Layouts.SliderKnob_LocationY_AP;
                this.repaint();
            }
            else  if(mode == 1) {
                mode = 1;
                knob_LocationX_Now = Layouts.SliderKnob_LocationX_Host;
                knob_LocationY_Now = Layouts.SliderKnob_LocationY_Host;
                this.repaint();
            }
            else {
                mode = 3;
                knob_LocationX_Now = Layouts.SliderKnob_LocationX_Default;
                knob_LocationY_Now = Layouts.SliderKnob_LocationY_Default;
                this.repaint();
            }
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        PointerInfo ms = MouseInfo.getPointerInfo();
        int msX = ms.getLocation().x;
        int msY = ms.getLocation().y;

        if(knob_LocationX_Now+Layouts.SliderKnob_SizeX/2<msX && msX<Layouts.SliderKnob_PointX_Host){
            knob_LocationX_Now = msX-Layouts.SliderKnob_SizeX/2;
        }

        else if(knob_LocationX_Now+Layouts.SliderKnob_SizeX/2>msX && msX>Layouts.SliderKnob_PointX_AP){
            knob_LocationX_Now = msX-Layouts.SliderKnob_SizeX/2;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
