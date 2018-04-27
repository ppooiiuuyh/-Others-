package network;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;


/**
 * Created by ppooi on 2017-04-29.
 */
public class ToGate_IMG_TCP implements Runnable {

    InetAddress serverIP;
    int serverPort;
    byte[] finalBytes;
    long timeout = 1000000000; // default timeout is infinite
    long startTime;
    String fileName;


    public ToGate_IMG_TCP(InetAddress serverIP, int serverPort){
        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.fileName = "fireimgforsend.jpg";
    }
    public ToGate_IMG_TCP(String serverHost, int serverPort){
        try {
            this.serverIP = InetAddress.getByName(serverHost);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        this.serverPort = serverPort;
        this.fileName = "fireimgforsend.jpg";
    }
    public ToGate_IMG_TCP(String serverHost, int serverPort,String fileName){
         this(serverHost,serverPort);
         this.fileName = fileName;
    }

    public void setTimeout(long timeout){
        this.timeout = timeout;
    }




    @Override
    public void run() {
        startTime = System.currentTimeMillis();


        final int HEADER_LEN = 4;
        final int DATE_LEN = 128;
        final int DATAOVERVIEW_LEN = 128;
        final int BLANK_LEN = 1024;
        final int TOTAL_LEN_WITHOUT_DATA = HEADER_LEN + DATE_LEN + DATAOVERVIEW_LEN + BLANK_LEN;
        final int DATA_LEN;
        /************************************************************
         * 데이터 구조
         * 헤더 [4]
         * 날짜 [128]
         * 데이터개요 [128]
         * 공백 [1024]
         * 데이터 [나머지전부]
         *
         * 1. 헤더만들어서 finalBytes 에 넣는다.
         * 2. 날짜만들어서 finalBytes 에 추가한다.
         * 3. 데이터개요만들어서 finalBytes 에 추가한다.
         * 4. 공백만들어서 finalBytes 에 추가한다.
         * 5. 데이터바이트만들어서 finalBytes 에 추가한다.
         *      5.1 사진을 찍는다.
         *      5.2 fireimgforsend.jpg 파일을 만든다.
         *      5.3 /images 폴더에 fireimgforsend + 현재시간 .jpg 파일로 복사시킨다.
         *      5.4 fireimgforsend.jpg 파일을 읽어서 finalBytes에 추가한다.
         * 6. finalBytes 검사
         ************************************************************/

        try {
            /************************************************************
             *헤더 [4]
             ************************************************************/
            finalBytes = makeFormWithString("imag", HEADER_LEN);


            /************************************************************
             *날짜 [128]
             ************************************************************/
            finalBytes = addByteArr(finalBytes,  makeFormWithBytes(new Date().toString().getBytes(), DATE_LEN));


            /************************************************************
             *데이터 개요 [128]
             ************************************************************/
            finalBytes = addByteArr(finalBytes, makeFormWithBytes("image type".getBytes(), DATAOVERVIEW_LEN));


            /************************************************************
             *공백[1024]
             ************************************************************/
            finalBytes = addByteArr(finalBytes, makeFormWithBytes(new byte[BLANK_LEN], BLANK_LEN));

            /************************************************************
             *데이터 [~]
             * 5. 데이터바이트만들어서 finalBytes 에 추가한다.
             *      5.1. 사진을 찍어서 fireimgforsend.jpg 파일을 만든다.
             *      5.2 /images 폴더에 fireimgforsend + 현재시간 .jpg 파일로 복사시킨다.
             *      5.3 fireimgforsend.jpg 파일을 읽어서 finalBytes에 추가한다.
             ************************************************************/

                /***************************************
                 * 5.1. 사진을 찍어서 fireimgforsend.jpg 파일을 만든다.
                * **************************************/
                Runtime runtime = Runtime.getRuntime();
                Process process = null;
                String command = new String("/home/pi/workspace/capturefireimg.sh");
                process = runtime.exec(command);
                process.waitFor();
                process.exitValue();


                /***************************************
                 * 5.2 /images 폴더에 복사한다
                 * **************************************/
                File source = new File("/home/pi/workspace/image/" + fileName);
                File dest = new File("/home/pi/workspace/image/backupIMGs/" +fileName+System.currentTimeMillis()+".jpg");
                Files.copy(source.toPath(),dest.toPath());



                /***************************************
                 *  5.3 fireimgforsend.jpg 파일을 읽어서 finalBytes에 추가한다.
                 * **************************************/
                FileInputStream fis;
                BufferedInputStream bis;
                File f = new File("/home/pi/workspace/image/" + fileName);

                fis = new FileInputStream(f);
                bis = new BufferedInputStream(fis);

                int len = 0;
                int control = 0;
                byte[] temp = new byte[1024];

                while ((len = bis.read(temp)) != -1) {
                    control += len;
                    if (control % 1024 == 0) {
                        System.out.println("데이터읽는중..." + control / 1024 + "KB");
                    }

                    byte[] data = new byte[len];
                    for (int i = 0; i < len; i++) data[i] = temp[i];
                    finalBytes = addByteArr(finalBytes, data);
                }

                System.out.println("contol : "+ control);

                byte[] temp2 = Integer.toString(control).getBytes();
                for(int i=0 ;i<temp2.length; i++){
                    finalBytes[i+260] = temp2[i];
                }


            /************************************************************
             *finalBytes 검사
             * 총 길이가 틀리면 보내지기전에 예외 발생시켜버린다. (미구현)
             ************************************************************/
            System.out.println(finalBytes.length);
            if (finalBytes.length < TOTAL_LEN_WITHOUT_DATA) {
                throw new IOException();
            }
            for (int i = 0; i < finalBytes.length; i++) {
                System.out.print((char) finalBytes[i]);
            }
            System.out.println();


            /************************************************************
             *finalBytes 를 TCP 소켓을통해 보낸다.
             ************************************************************/
            Socket socket = new Socket(serverIP, serverPort);
            DataOutputStream di = new DataOutputStream(socket.getOutputStream());
            di.write(finalBytes);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }



    byte[] addByteArr(byte[] barr1, byte[] barr2){
        byte[] result = new byte[barr1.length + barr2.length];

        for(int i=0; i<barr1.length; i++){
            result[i] = barr1[i];
        }
        for(int i=barr1.length; i<barr1.length+ barr2.length ; i++){
            result[i] = barr2[i-barr1.length];
        }

        return result;
    }


    public byte[] makeFormWithString(String str,int headerLen){
        byte[] result = new byte[headerLen];
        byte[] strTemp = str.getBytes();
        for(int i=0; i<strTemp.length; i++){
            result[i] = strTemp[i];
        }
        return result;
    }

    public byte[] makeFormWithBytes(byte[] b,int len){
        byte[] result = new byte[len];
        for(int i=0; i<b.length; i++){
            result[i] = b[i];
        }
        return result;
    }







}
