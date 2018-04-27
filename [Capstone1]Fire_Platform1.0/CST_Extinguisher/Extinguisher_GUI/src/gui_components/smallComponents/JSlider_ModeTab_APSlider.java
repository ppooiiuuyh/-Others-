/*
package components.smallComponents;

import accesory.Interface_Components;
import components.panels.JPanel_ModeTab_FirstPanel;
import data.Layouts;
import main.Manager;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;

*/
/**
 * Created by ppooi on 2017-03-26.
 *//*

public class JSlider_ModeTab_APSlider extends JSlider implements Interface_Components{

    */
/*Upper Components*//*

    JPanel_ModeTab_FirstPanel jPanel_modeTab_firstPanel;

    */
/*Components*//*



    */
/*Local variables*//*

    public JSlider_ModeTab_APSlider(JPanel_ModeTab_FirstPanel jPanel_modeTab_firstPanel){
        init_UpperComponents(jPanel_modeTab_firstPanel);
        init_Comps();
        init_Locals();
        init_This();
    }





    public void init_UpperComponents(JPanel_ModeTab_FirstPanel jPanel_modeTab_firstPanel) {
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

    }

    @Override
    public void init_This() {
//        this.setBackground(Color.black);
        this.setLocation((int)Layouts.Slider_LocationX,50);
        this.setSize((int)Layouts.Slider_SizeX,50);

//this.setUI(new JSliderUI_APSliderCustomUI(this));
*/
/*
        Icon icon = new ImageIcon("test2.png");
        UIDefaults defaults = UIManager.getDefaults();
        defaults.put("Slider.horizontalThumbIcon", icon);*//*

        setUI(new BasicSliderUI(this) {
            public void paintThumb(Graphics g) {
                super.paintThumb(g);

                Image image = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("knob1.png"));
                g.drawImage(image,thumbRect.x-50 ,thumbRect.y,100,100,null);

            }
        });


    }


    public JPanel_ModeTab_FirstPanel getUpperComponent() {
        return jPanel_modeTab_firstPanel;
    }

    @Override
    public Manager getManager() {
        return null;
    }
}
*/
