package gui_components.panels;

import gui_components.smallComponents.JPanel_ModeTab_APSlider;
import data.Layouts;
import main.Manager;

import javax.swing.*;

/**
 * Created by ppooi on 2017-03-26.
 */
public class JPanel_ModeTab_FirstPanel extends JPanel {

    /*Upper Components*/
    JPanel_ModeTab jPanel_modeTab;
    Manager manager;

    /*Components*/
    JPanel_ModeTab_APSlider jPanel_modeTab_apSlider;

    public JPanel_ModeTab_FirstPanel(JPanel_ModeTab jPanel_modeTab){
        init_UpperComponents(jPanel_modeTab);
        init_Components();
        init_Locals();
        init_This();
    }

    public void init_UpperComponents(JPanel_ModeTab jPanel_modeTab){
        this.jPanel_modeTab = jPanel_modeTab;
        this.manager = jPanel_modeTab.getManager();
    }
    public void init_Components(){
        jPanel_modeTab_apSlider = new JPanel_ModeTab_APSlider(this);
        this.add(jPanel_modeTab_apSlider);
    }
    public void init_Locals(){

    }
    public void init_This(){
/*

		TitledBorder border = BorderFactory.createTitledBorder("APMode / HostMode Switch");
		border.setTitleColor(Color.black);
		this.setBorder(border);
*/
		this.setLayout(null);
		this.setSize((int)Layouts.ModeTab_FirstPanel_SizeX,(int)Layouts.ModeTab_FirstPanel_SizeY);

//        MyUtillities.showEffectiveArea(Layouts.ModeTab_FirstPanel_PanelBounds,this);

    }




    public JPanel_ModeTab getUpperComponents(){
        return jPanel_modeTab;
    }
    public Manager getManager(){
        return manager;
    }


}