package network;

import java.io.IOException;
import java.net.*;

/**
 * Created by ppooi on 2017-04-03.
 */

public class MSGsender {

    int serverPort;
    String serverHost;
    InetAddress serverIP;
    Thread th_togate = new Thread();


    public MSGsender(){
        init();
    }
    public MSGsender(String serverHost, int serverPort){
        init(serverHost,serverPort);
    }

    public void init(){
        serverPort = 54321;
        serverHost = "127.0.0.1";
        try {
            serverIP = InetAddress.getByName(serverHost);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void init(String serverHost, int serverPort){
        this.serverPort = serverPort;
        this.serverHost = serverHost;
        try {
            serverIP = InetAddress.getByName(serverHost);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }



    /********************************************************
     * UDP를통해 4바이트 헤더메세지만 보내는 함수
    * *******************************************************/
    public void sendMSG_justHeader(String str){
        try {
            byte[] packetBytes = makeMSGHeader(str);

            DatagramSocket senderSocket = new DatagramSocket();
            senderSocket.send(new DatagramPacket(packetBytes,packetBytes.length,serverIP,serverPort));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /********************************************************
     * 4바이트 헤더메세지폼을 만든다
     * *******************************************************/
    byte[] makeMSGHeader(String str){
        byte[] result = new byte[4];
        byte[] strTemp = str.getBytes();
        for(int i=0; i<strTemp.length; i++){
            result[i] = strTemp[i];
        }
        return result;
    }







    /********************************************************
     * TCP를통해 Gate로 사진을 찍어 이미지를 보낸다. 처리 스레드는 delay만큼 기다린다.
     * *******************************************************/
    public void sendMSGToGate_hasDelayPadding(long delay){

            th_togate = new Thread(new ToGate_IMG_TCP(serverIP, serverPort));
            th_togate.start();
    }

    public Thread getTh_togate(){
        return th_togate;
    }


}
