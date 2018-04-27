package accesory;

import main.Manager;

import javax.swing.*;

/**
 * Created by ppooi on 2017-03-26.
 */
public interface Interface_Components {

    public void init_UpperComponents();
    public void init_Comps();
    public void init_Locals();
    public void init_This();

    public JComponent getUpperComponent();
    public Manager getManager();

}
