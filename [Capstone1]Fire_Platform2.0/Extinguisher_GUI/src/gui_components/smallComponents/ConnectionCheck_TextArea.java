package gui_components.smallComponents;

import accesory.Interface_Components;
import accesory.PingFactory;
import data.Layouts;
import main.Manager;

import javax.swing.*;
import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by ppooi on 2017-03-27.
 */
public class ConnectionCheck_TextArea extends JTextArea implements Runnable , Interface_Components{


    /*Upper Components*/
    JPanel_MainTab_ConnetionCheck jPanel_mainTab_connetionCheck;
    Manager manager;

    /*Components*/

    /*Local variables*/
    String APaddress;
    double rtt;
    Thread thread;


    public ConnectionCheck_TextArea(JPanel_MainTab_ConnetionCheck jPanel_mainTab_connetionCheck){
        init_UpperComponents(jPanel_mainTab_connetionCheck);
        init_Comps();
        init_Locals();
        init_This();
    }


    @Override
    public void run() {
        while(true) {

            InetAddress ip = null;
            try {
                ip = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

            String strTemp = "Current ip is " +ip.getHostAddress()+".\nDest ip is " +APaddress+"\n\n";

            rtt = (double)(PingFactory.getRTT(APaddress))/1000000 ;
            System.out.println(rtt);
            if (rtt > 3000) {
                this.setText(strTemp + "disconnected");
            } else if (rtt < 0) {
                this.setText(strTemp + "rtt is less than 0, there is something wrong \n \n " +APaddress +" is NOT reachable");
            } else {
                this.setText(strTemp + "RTT is " + rtt + "ms. \n" + "It's connected");
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void init_UpperComponents(JPanel_MainTab_ConnetionCheck jPanel_mainTab_connetionCheck){
        this.jPanel_mainTab_connetionCheck = jPanel_mainTab_connetionCheck;
        this.manager = manager;
    }
    @Override
    public void init_UpperComponents() {

    }

    @Override
    public void init_Comps() {

    }

    @Override
    public void init_Locals() {
        APaddress = "222.222.0.1";
        //APaddress = "172.217.24.142"; // google ip for test
        rtt =1000;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void init_This() {
        this.setLocation(0,0);
        this.setSize((int)Layouts.ConnectionCheck_SizeX,(int)Layouts.ConnectionCheck_SizeY);
        this.setWrapStyleWord(true);
        this.setBackground(Color.LIGHT_GRAY);
        this.setLineWrap(true);
        this.setEditable(false);

        this.setText("disconnecteddddddddddddddddddddddddddd");

    }

    @Override
    public JPanel_MainTab_ConnetionCheck getUpperComponent() {
        return jPanel_mainTab_connetionCheck;
    }

    @Override
    public Manager getManager() {
        return manager;
    }
}
