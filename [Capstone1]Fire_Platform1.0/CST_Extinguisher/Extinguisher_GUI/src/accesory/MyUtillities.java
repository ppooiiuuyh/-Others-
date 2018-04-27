package accesory;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ppooi on 2017-03-26.
 */
public class MyUtillities {
    public MyUtillities(){

    }

    public static void showEffectiveArea(Rectangle rect,JComponent jComponent){
        JPanel testPanel = new JPanel();
        testPanel.setBounds(rect);
        testPanel.setBackground(Color.black);
        jComponent.add(testPanel);
    }

    public static boolean isInRect(Rectangle rect,int x, int y){
        if(rect.getX() < x && rect.getY() < y && rect.getX()+rect.getWidth() > x && rect.getY()+rect.getHeight() > y ){
            return true;
        }
        else{
            return false;
        }
    }
}
