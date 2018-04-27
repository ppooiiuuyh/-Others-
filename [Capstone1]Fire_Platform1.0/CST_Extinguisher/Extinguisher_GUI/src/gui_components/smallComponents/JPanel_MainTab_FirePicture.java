package gui_components.smallComponents;

import accesory.Interface_Components;
import gui_components.panels.JPanel_MainTab_FirstPanel;
import data.Layouts;
import main.Manager;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ppooi on 2017-03-27.
 */
public class JPanel_MainTab_FirePicture extends JPanel implements Interface_Components {

    /*Upper Components*/
    JPanel_MainTab_FirstPanel jPanel_mainTab_firstPanel;
    Manager manager;
    /*Components*/

    /*Local variables*/
    Image firePicture;


    public JPanel_MainTab_FirePicture(JPanel_MainTab_FirstPanel jPanel_mainTab_firstPanel) {
        init_UpperComponents(jPanel_mainTab_firstPanel);
        init_Comps();
        init_Locals();
        init_This();

    }

    public void init_UpperComponents(JPanel_MainTab_FirstPanel jPanel_mainTab_firstPanel){
        this.jPanel_mainTab_firstPanel = jPanel_mainTab_firstPanel;
        manager = jPanel_mainTab_firstPanel.getManager();
    }

    @Override
    public void init_UpperComponents() {

    }

    @Override
    public void init_Comps() {

    }

    @Override
    public void init_Locals() {
        firePicture = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("firePicture.png"));
    }

    @Override
    public void init_This() {
        this.setBounds(Layouts.MainTab_FirstPanel_PanelBounds);

    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.drawImage(firePicture,Layouts.FirePicture_LocationX,Layouts.FirePicture_LocationY,(int)Layouts.FirePicture_SizeX,(int)Layouts.FirePicture_SizeY,null);

        this.repaint();
    }




    @Override
    public JPanel_MainTab_FirstPanel getUpperComponent() {
        return jPanel_mainTab_firstPanel;
    }

    @Override
    public Manager getManager() {
        return manager;
    }
}
