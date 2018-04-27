package network;

import main.Manager;

/**
 * Created by ppooi on 2017-04-03.
 */
public class MSGReceiver {
    Manager manager;
    Thread receiverThead;


    public MSGReceiver(Manager manager){
        init_Upper(manager);
        init_This();
    }

    public void init_Upper(Manager manager){
        this.manager = manager;
    }
    public void init_This(){
        this.receiverThead = new Thread(new Runnable_UDPReceiver(manager));
    }

    public void receiverThreadStart(){
        receiverThead.start();
    }
    public void receiverThreadStop(){
        try {
            receiverThead.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
