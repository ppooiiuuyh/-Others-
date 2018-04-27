package main;

import dbinterfaces.MariaDBManager;
import network.TossFromGateToDB;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ppooi on 2017-05-01.
 */
public class DBInterfaceServer {
    public static void main(String[] argv){



        /*************************************************
         * main 무한루프
         *
         * TCP 수신을 대기 하다가 연결이 들어오면 처리 스레드를 돌린다.
         * ***********************************************/
        try {
            ServerSocket serverSocket = null;
            Socket socket = null;
            serverSocket = new ServerSocket(65432);


            MariaDBManager mariaDBManager = new MariaDBManager();

            while (true) {
                System.out.println("대기");

                socket = serverSocket.accept();
                Thread t = new Thread(new TossFromGateToDB(socket,10000));
                t.start();
            }

        }catch (IOException e) {
                e.printStackTrace();
        }


    }
}
