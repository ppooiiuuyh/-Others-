package gui_components.smallComponents;

import accesory.Interface_Components;
import gui_components.panels.JPanel_ConnectionTab;
import data.Layouts;
import main.Manager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Created by ppooi on 2017-03-27.
 */
public class JTable_ConnectionTab_ConnectionState extends JTable implements Interface_Components{


    /*Upper Components*/
    JPanel_ConnectionTab jPanel_connetionTab;
    Manager manager;

    /*Components*/


    /*Local variables*/

    String columnNames[];
    Object rowData[][];

    DefaultTableModel defaultTableModel;



    public JTable_ConnectionTab_ConnectionState(JPanel_ConnectionTab jPanel_connetionTab){
        init_UpperComponents(jPanel_connetionTab);
        init_Comps();
        init_Locals();
        init_This();
    }

    public void init_UpperComponents(JPanel_ConnectionTab jPanel_connetionTab){
        this.jPanel_connetionTab = jPanel_connetionTab;
        this.manager = jPanel_connetionTab.getManager();
    }
    @Override
    public void init_UpperComponents() {

    }

    @Override
    public void init_Comps() {

    }

    @Override
    public void init_Locals() {

        columnNames = new String[]{ "IPaddress", "Type", "ID"};
        rowData = new Object[20][3];
        rowData[0] = new String[]{ "222.222.0.13", "Extinguisher", "b8:27:eb:cc:87:10"};
        rowData[1] = new String[]{ "222.222.0.20", "FlameSensor", "b8:27:eb:cc:87:14"};
        rowData[2] = new String[]{ "222.222.0.1", "Extinguisher", "b8:27:eb:b1:5c:10"};
        rowData[3] = new String[]{ "222.222.0.18", "FlameSensor", "b8:27:eb:a2:5b:02"};
        defaultTableModel = new DefaultTableModel(rowData, columnNames);
    }

    @Override
    public void init_This() {
        this.setModel(defaultTableModel);
        this.setBounds(Layouts.ConnectionState_Bounds);
        this.setEnabled(false);
        this.setLayout(null);

    }

    @Override
    public JPanel_ConnectionTab getUpperComponent() {
        return jPanel_connetionTab;
    }

    @Override
    public Manager getManager() {
        return manager;
    }
}
