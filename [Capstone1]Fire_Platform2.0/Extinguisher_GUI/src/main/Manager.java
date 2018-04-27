package main;


import gui_components.frames.MainFrame;
import network.MSGReceiver;
import sounder.Sounder;

/**
 * Created by ppooi on 2017-03-26.
 */
public class Manager {
    MainFrame mainFrame;
    MSGReceiver msgReceiver;
    Sounder sounder;

    public Manager(){
        init();
    }

    public void init(){
        init_GUI();
        init_Network();
        init_Sounder();
    }

    public void init_GUI(){
        mainFrame = new MainFrame( this);
    }
    public void init_Network(){
        msgReceiver = new MSGReceiver(this);
    }
    public void init_Sounder(){
        sounder = new Sounder(this);
    }

    public void process(){
        mainFrame.repaint();
        msgReceiver.receiverThreadStart();
    }



    public Sounder getSounder(){
        return sounder;
    }




}

