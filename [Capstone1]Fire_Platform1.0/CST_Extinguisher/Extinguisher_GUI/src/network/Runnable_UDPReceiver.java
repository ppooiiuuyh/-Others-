package network;

import main.Manager;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by ppooi on 2017-04-03.
 */
public class Runnable_UDPReceiver implements Runnable {

    Manager manager;
    int serverPort = 54321;
    DatagramSocket serverSocket;
    String sentence;
    byte[] receiveData;

    public Runnable_UDPReceiver(Manager manager){
        this.manager = manager;

        serverPort = 54321;


        try {
            serverSocket = new DatagramSocket(serverPort);
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }




    @Override
    public void run() {
        while (true) {

            receiveData = new byte[4];

            try {
                // read from the socket stream
                // and print to the console
                DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);

                serverSocket.receive(receivePacket);
                sentence = new String(receivePacket.getData());

                                //출력
                System.out.println(sentence);

            }
            catch (IOException e) {
                //e.printStackTrace();
            }

           if(manager.getSounder().isRunning() == false){
                manager.getSounder().startSound();

               System.out.println(manager.getSounder().isRunning());

            }




        }
    }

}
