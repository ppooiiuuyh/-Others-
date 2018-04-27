package network;

import java.io.*;
import java.net.*;

/**
 * Created by ppooi on 2017-04-30.
 */
public class TossFromDetectorToDBMnagerServer implements  Runnable {
    Socket socket;
    long timeout = 100000000; // default timeout is infinite;
    long starttime;



    public TossFromDetectorToDBMnagerServer(Socket socket) {
        this.socket = socket;
    }
    public TossFromDetectorToDBMnagerServer(Socket socket, long timeout) {
        this.socket = socket; this.timeout = timeout;
    }



    @Override
    public void run() {
        starttime = System.currentTimeMillis();

        /************************************************************
         *데이터 구조
         * 처리타입 [4]
         * 날짜 [128]
         * 데이터개요 [128]
         * 공백 [1024]
         * 데이터 [나머지전부]
         *
         *
         * 1. 소켓을통해서 인풋스트림생성
         * 2. 인풋스트림에 읽을 내용이 있을때까지 대기
         * 3. 스트림을통해 읽어들여서 finalRecvBytes를 완성하고 헤더데이타의 종류에따라 적합한 처리함수를 실행하고 finalSendBtes를 리턴받는다.
         * 4. finalbytes를 db관리 서버로 보낸다.
         ************************************************************/
        try {

            DataInputStream dis;
            /********************************************************
             * 1. 소켓을통해서 인풋스트림생성
            * *******************************************************/
                dis = new DataInputStream(socket.getInputStream());



            /********************************************************
             * 2. 인풋스트림에 읽을 헤더내용이 있을때까지 대기 (한번에 최대 65536바이트까지만 대기할 수 있다.)
             * *******************************************************/
                   while(dis.available()<1284){
                       try {
                           Thread.sleep(10);
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                       if(System.currentTimeMillis() -starttime >timeout){ throw new Exception("Time out");} //timeout
                    }



            byte[] finalSendBytes = new byte[0];
            /********************************************************
             * 3. 스트림을통해 읽어들여서 finalRecvBytes를 완성하고 헤더데이타의 종류에따라 적합한 처리함수를 실행하고 finalSendBtes를 리턴받는다.
             * *******************************************************/

                byte[] finalRecvBytes = new byte[0];
                /**********************************************
                 * 3.1. 헤더 읽어들여서 finalRecvBytes에 추가시킨다.
                 ************************************************/
                    byte[] headerTemp = new byte[1284];
                    dis.read(headerTemp);
                    finalRecvBytes = addByteArr(finalRecvBytes,headerTemp);


                int dataSize =0;
                /**********************************************
                 * 3.2. 헤더에서 blank부분 ( blank 부분을 데이터사이즈정보로 사용하기로 하였다) 을통해 순수 데이터의 사이즈를 구한다.
                 ************************************************/
                    byte[] blank = new byte[1024];
                        for( int i=0; i<1024; i++ ) blank[i] = headerTemp[i+4+128+128];
                    String dataSizeStr = new String(blank);
                    System.out.println(dataSizeStr.trim());

                    dataSize = Integer.parseInt(dataSizeStr.trim());
                    System.out.println("[wanted pure data size ] : "+dataSize);




                /**********************************************
                 * 3.3. 구해진 데이터의 사이즈만큼 추가로 읽는다.
                 ************************************************/

                    byte[] getbytes = new byte[1024];
                    int len =0 ;
                    while(dis.available() > 0 || finalRecvBytes.length<dataSize+1284){
                        len = dis.read(getbytes);
                        byte[] temp = new byte[len];
                        for(int i=0; i<temp.length; i++) temp[i] = getbytes[i];
                        finalRecvBytes = addByteArr(finalRecvBytes,temp);

                        if(System.currentTimeMillis() -starttime >timeout){ throw new Exception("Time out");} //timeout
                    }
                    System.out.println("[read total data size(maybe wanted size + header size ] : "+finalRecvBytes.length);



                /**********************************************
                 * 3.4. finalRecvBytes 검사.
                 *  - 조건1 : total data size 가 헤더 + pure data size보다 작으면 에러
                 ************************************************/
                    if(finalRecvBytes.length<dataSize+1284 ){
                        throw new Exception("[error in process img message : less than wanted total data size]");
                    }


                /**********************************************
                 * 3.5. 헤버 내용에따라 적절한 처리함수(인자로 finalRecvBytes를 넘긴다) 를 선택해서 처리한다..
                 ************************************************/
                    byte[] typeBytes = new byte[4];
                    for(int i=0; i<4; i++) typeBytes[i] = finalRecvBytes[i];

                    if(hasSameContents_byteArr(typeBytes,"imag".getBytes())){
                           finalSendBytes = processForImg(finalRecvBytes);
                    }
                    else{
                       throw new IOException();
                    }


            /********************************************************
             * 4. finalSendBytes를 db관리 서버로 보낸다.
             * *******************************************************/

                System.out.println("[finalSendBytes]");
                for(byte b : finalSendBytes) System.out.print((char)b);
                System.out.println();


                Socket socket = new Socket("127.0.0.1",65432);
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.write(finalSendBytes);




        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    public byte[] processForImg(byte[] finalRecvBytes) throws IOException {

        /***************************************************************
         * header 타입이 imag 였을때 처리함수.
         * finalbytes 와 dis를 인자로 받는다.
         * 처리 완료되어 보내기만 하면 될 바이트배열을 finalbytes에 답아 리턴한다.
         * 문제 발생시에 상위로 예외를 throw 한다.
         *
         * -----------------------------------
         * 데이터 구조
         * 처리 타입 [4]
         * 날짜 [128]
         * 데이터개요 [128]
         * 공백 [1024]
         * 데이터 [나머지전부]
         *------------------------------------
         * 1. finalRecvBbytes 로부터 날짜 읽는다
         * 2. finalRecvBbytes 로부터 데이터개요 읽는다.
         * 3. finalRecvBbytes 로부터 공백 읽는다.
         * 4. finalRecvBbytes 로부터 데이터(나머지 전부) 읽는다.
         * 5. 디비관리서버에 보내기위해 재구성하여 최종적으로 보내질 finalbytes를 완성한다.
         * 6. finalbytes 리턴
        * ***************************************************************/




        byte[] type = new byte[4];
        byte[] date = new byte[128];
        byte[] dataoverview = new byte[128];
        byte[] blank = new byte[1024];
        byte[] data = new byte[finalRecvBytes.length - type.length - date.length - dataoverview.length - blank.length];
        /***************************************
         * 1~ 4. finalRecvBbytes 로부터 데이터 읽는다.
         * *************************************/
            for( int i=0; i<4 ; i++) type[i] = finalRecvBytes[i];
            for( int i=0; i<128; i++ ) date[i] = finalRecvBytes[i+4];
            for( int i=0; i<128; i++ ) dataoverview[i] = finalRecvBytes[i+4+128];
            for( int i=0; i<1024; i++ ) blank[i] = finalRecvBytes[i+4+128+128];
            for( int i=0; i< finalRecvBytes.length - (4+128+128+1024) ; i++) data[i] = finalRecvBytes[i + 4+128+128+1024];

            for(int i=0; i<type.length ; i++) System.out.print((char)type[i]);
            System.out.println();
            for(int i=0; i<date.length ; i++) System.out.print((char)date[i]);
            System.out.println();
            for(int i=0; i<dataoverview.length ; i++) System.out.print((char)dataoverview[i]);
            System.out.println();
            for(int i=0; i<blank.length ; i++) System.out.print((char)blank[i]);
            System.out.println();
            for(int i=0; i<data.length ; i++) System.out.print((char)data[i]);


        /***************************************
         * 1.2~4.2 파일로 출력
         * -----------------------------------
         * 데이터 구조
         * 처리 타입 [4]
         * 날짜 [128]
         * 데이터개요 [128]
         * 공백 [1024]
         * 데이터 [나머지전부]
         *------------------------------------
         * *************************************/
            FileOutputStream fos;

            fos = new FileOutputStream("pathname"+System.currentTimeMillis()+".jpg");
            fos.write(data);
            fos.close();




        byte[] finalSendBytes = new byte[0];
        /***************************************
         * 5. 디비관리서버에 보내기위해 재구성하여 최종적으로 보내질 finalbytes를 완성한다.
         * -----------------------------------
         * 데이터 구조
         *
         * 처리 타입 [4]
         * 날짜 [128]
         * 게이트 맥주소 [128]
         * 공백 [1024]
         * 데이터 [나머지전부]
         * ------------------------------------
         * *************************************/
            finalSendBytes = addByteArr(finalSendBytes,type);
            finalSendBytes = addByteArr(finalSendBytes,date);
            finalSendBytes = addByteArr(finalSendBytes,makeFormWithBytes(getLocalMacAddress(),128));
            finalSendBytes = addByteArr(finalSendBytes,blank);
            finalSendBytes = addByteArr(finalSendBytes,data);


        /***************************************
         * 6. finalbytes 리턴
         * *************************************/
            return finalSendBytes;
    }








    /*****************************************************************************************************************
     * 기타 유틸 매서드
     *
     *
    * ***************************************************************************************************************/

        /*******************************************************************************
         * 두 byte타입 배열이 같은 값을 가지고 있는지 비교하는 매서드
        * ******************************************************************************/
            public boolean hasSameContents_byteArr(byte[] b1, byte[] b2){
                boolean result = true;

                if(b1.length != b2.length) result = false;
                for(int i=0; i<b1.length; i++) {
                    if (b1[i] != b2[i]) result = false;
                }

                return result;
            }




        /*******************************************************************************
         * 두 byte타입 배열을 합쳐서 하나의 배열로 반환하는 매서드
         * ******************************************************************************/
            public byte[] addByteArr(byte[] barr1, byte[] barr2){
                byte[] result = new byte[barr1.length + barr2.length];

                for(int i=0; i<barr1.length; i++){
                    result[i] = barr1[i];
                }
                for(int i=barr1.length; i<barr1.length+ barr2.length ; i++){
                    result[i] = barr2[i-barr1.length];
                }
                return result;
            }




        /*******************************************************************************
         * 데이터를 len만큼의 길이를가지는 배열안에 넣는 매서드
         * ******************************************************************************/
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





        /*******************************************************************************
         * 로컬맥주소를 char타입 값을가지는 byte[]로 반환하는 매서드
         * ******************************************************************************/
            //로컬 맥주소를 12자리 char값 byte 배열로 반환한다.
            public byte[] getLocalMacAddress() throws IOException {
                byte[] result = new byte[12];

                byte[] hexMac = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();

                for(int i=0 ; i<6; i++){
                    result[i*2] = hexByteToCharByte((byte) (hexMac[i]>>>4 & 15));
                    result[i*2+1] = hexByteToCharByte((byte) (hexMac[i] & 15));
                }


                if(result.length!=12){
                    throw new IOException();
                }
                return result;
            }

            //한자리 16진수를 대응하는 char타입 값으로 변환
            public byte hexByteToCharByte(byte hexMac){
                byte result = 0;

                if(hexMac<10) result = (byte) (hexMac + '0');
                else result = (byte) (hexMac + 'A' -10);

                return result;
            }




}
