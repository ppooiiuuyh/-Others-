package main;

/**
 * Created by ppooi on 2017-04-03.
 */

//import com.pi4j.io.gpio.GpioController;
//import com.pi4j.io.gpio.GpioPinDigitalInput;
import network.*;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Detector_forTest {

    public static void main(String[] argv){


        /*************************************************************************************************
         * main loop
         *
         * 1. gipo pin 세팅
         * 2. pin 값에 따라 mode 설정
         * 3. flame sensor pin 값에 따라 메세지 보낸다.
         *      3.1. 화재가 감지될 경우
         *        1. UDP로 fire 데이터를 broadcating 한다.
         *        2. 최소 interval 만큼의 간격으로 사진을찍어메세지를 보낸다.
         *      3.2. 화재가 감지되지 않을경우
         *
        * ************************************************************************************************/




        // create gpio controller
        /*
        final GpioController gpio;
        final GpioPinDigitalInput mode;
        final GpioPinDigitalInput flame;*/
        /****************************************************************
         * 1. gipo pin 세팅
        * ***************************************************************/
        // create gpio controller
       /* gpio = GpioFactory.getInstance();
        mode = gpio.provisionDigitalInputPin(RaspiPin.GPIO_04);
        flame = gpio.provisionDigitalInputPin(RaspiPin.GPIO_01);
        */


        /****************************************************************
         * 2. pin 값에 따라 mode 설정
         * ***************************************************************/
      /*  Runtime runtime = Runtime.getRuntime();
        Process process = null;

        try {
            if(mode.isHigh()) {
                process = runtime.exec("sudo /home/pi/workspace/runAPmode");
                process.waitFor();
            }
            else {
                process = runtime.exec("sudo /home/pi/workspace/runCLmode");
                process.waitFor();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/



        /****************************************************************
         * 3. flame sensor pin 값에 따라 메세지 보낸다.
         * ***************************************************************/
        /*while (true) {
            //화재 미감지
            if (flame.isHigh()) {
                System.out.println("high");
                System.out.println();
            }
            //화재 감지
            else {
                System.out.println("low");
                if(!m.getTh_togate().isAlive())
                m.sendMSGToGate_hasDelayPadding(10);
                m.sendMSG_justHeader("fire");
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

        MyIntervaledThread sendImg_toGate_TCP = new MyIntervaledThread();
        MyIntervaledThread sendFire_toBroad_UDP = new MyIntervaledThread();

        while(true){
            //불났을때
            if(true) {
                if(!sendImg_toGate_TCP.isAlive()) {
                    sendImg_toGate_TCP = new MyIntervaledThread(new ToGate_IMG_TCP("127.0.0.1", 54321),5000);
                    sendImg_toGate_TCP.start();
                }

                if(!sendFire_toBroad_UDP.isAlive()){
                    sendFire_toBroad_UDP = new MyIntervaledThread(new ToBroad_HeaderMSG_UDP("222.222.0.255", 54321,"fire"),10);
                    sendFire_toBroad_UDP.start();
       
                }

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //불 안났을때
            else{

            }

         }

    }

}
