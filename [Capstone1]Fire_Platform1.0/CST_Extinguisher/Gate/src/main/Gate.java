package main;

import network.TossFromDetectorToDBMnagerServer;
import java.io.*;
import java.net.*;

public class Gate{

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = null;
            Socket socket = null;
            serverSocket = new ServerSocket(54321);


            while(true) {
                System.out.println("대기");
                socket = serverSocket.accept();

                Thread t =  new Thread(new TossFromDetectorToDBMnagerServer(socket,10000));

                t.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

