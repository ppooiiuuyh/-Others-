package gui_components.smallComponents;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;

/**
 * Created by ppooi on 2017-03-27.
 */
public class JSliderUI_APSliderCustomUI extends BasicSliderUI {

  /*  private BasicStroke stroke = new BasicStroke(1f, BasicStroke.CAP_ROUND,
            BasicStroke.JOIN_ROUND, 0f, new float[]{1f, 2f}, 0f);
*/
    public JSliderUI_APSliderCustomUI(JSlider b) {
        super(b);
    }
/*
    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        super.paint(g, c);
    }

    @Override
    protected Dimension getThumbSize() {
        return new Dimension(12, 16);
    }*/
/*
    @Override
    public void paintTrack(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Stroke old = g2d.getStroke();
        g2d.setStroke(stroke);
        g2d.setPaint(Color.BLACK);
        if (slider.getOrientation() == SwingConstants.HORIZONTAL) {
            g2d.drawLine(trackRect.x, trackRect.y + trackRect.height / 2,
                    trackRect.x + trackRect.width, trackRect.y + trackRect.height / 2);
        } else {
            g2d.drawLine(trackRect.x + trackRect.width / 2, trackRect.y,
                    trackRect.x + trackRect.width / 2, trackRect.y + trackRect.height);
        }
        g2d.setStroke(old);
    }*/

    @Override
    public void paintThumb(Graphics g) {


     //   Graphics2D g2d = (Graphics2D) g;


        int x1 = thumbRect.x + 2;
        int x2 = thumbRect.x + thumbRect.width - 2;
        int width = thumbRect.width - 4;
        int topY = thumbRect.y + thumbRect.height / 2 - thumbRect.width / 3;
/*

        Image image = Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("test2.png"));
        g.drawImage(image,0,0,20,20,null);
*/


        /*  GeneralPath shape = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
        shape.moveTo(x1, topY);
        shape.lineTo(x2, topY);
        shape.lineTo((x1 + x2) / 2, topY + width);
        shape.closePath();
        g2d.setPaint(new Color(81, 83, 186));
        g2d.fill(shape);
        Stroke old = g2d.getStroke();
        g2d.setStroke(new BasicStroke(2f));
        g2d.setPaint(new Color(131, 127, 211));
        g2d.draw(shape);
        g2d.setStroke(old);*/
    }
}
