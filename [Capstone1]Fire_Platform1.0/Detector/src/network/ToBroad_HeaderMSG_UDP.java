package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by ppooi on 2017-04-30.
 */
public class ToBroad_HeaderMSG_UDP implements Runnable {

    String serverHost;
    InetAddress serverIP;
    int serverPort;
    String msg;

    public ToBroad_HeaderMSG_UDP(String serverHost, int serverPort){
        try {
            serverIP = InetAddress.getByName(serverHost);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        this.serverPort = serverPort;
        msg = "fire";
    }
    public ToBroad_HeaderMSG_UDP(String serverHost, int serverPort, String msg){
        try {
            serverIP = InetAddress.getByName(serverHost);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        this.serverPort = serverPort;
        this.msg = msg;
    }

    @Override
    public void run() {
        sendMSG_justHeader(msg);
    }


    /********************************************************
     * 4바이트 헤더메세지폼 byte배열을 데이터그램 소켓으로 보낸다.
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

}
