package network;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by ppooi on 2017-05-01.
 */
public class TossFromGateToDB implements Runnable {

    Socket socket;
    long timeout = 100000000; // default timeout is infinite
    long starttime = System.currentTimeMillis();

    public TossFromGateToDB (Socket socket){
        this.socket = socket;
    }
    public TossFromGateToDB (Socket socket, long timeout){
        this.socket = socket; this.timeout = timeout;
    }


    @Override
    public void run() {
        starttime = System.currentTimeMillis();
        /************************************************************
         *데이터 구조
         * 처리 타입 [4]
         * 날짜 [128]
         * 데이터개요(맥주소) [128]
         * 공백(데이터사이즈) [1024]
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

                    if(System.currentTimeMillis() - starttime > timeout){throw new Exception("time out");}//timeout
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


                        if(System.currentTimeMillis() - starttime > timeout){throw new Exception("time out");}//timeout

                    }
                    System.out.println("[read total data size(maybe wanted size + header size ] : "+finalRecvBytes.length);



                    /**********************************************
                     * 3.4. finalRecvBytes 검사.
                     *  - 조건1 : total data size 가 헤더 + pure data size보다 작으면 에러
                     ************************************************/
                    if(finalRecvBytes.length<dataSize+1284 ){
                        throw new IOException("[error in process img message : less than wanted total data size]");
                    }



                    /**********************************************
                     * 3.5. 헤버 내용에따라 적절한 처리함수(인자로 finalRecvBytes를 넘긴다) 를 선택해서 처리한다..
                     ************************************************/
                            byte[] headerBytes = new byte[4];
                            for(int i=0; i<4; i++) headerBytes[i] = finalRecvBytes[i];

                            if(hasSameContents_byteArr(headerBytes,"imag".getBytes())){
                              processForImg(finalRecvBytes);
                            }
                            else{
                                throw new IOException();
                            }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

    }



    /*****************************************************************************************************************
     * 메세지 처리 메서드
     *
     *
     * ***************************************************************************************************************/

        /*******************************************************************************
         * 프로토콜 타입 : imag
         * ******************************************************************************/
             public void processForImg(byte[] finalRecvBytes) throws IOException {

                 byte[] type = new byte[4];
                 byte[] date = new byte[128];
                 byte[] macaddress = new byte[128];
                 byte[] blank = new byte[1024];
                 byte[] data = new byte[finalRecvBytes.length - type.length - date.length - macaddress.length - blank.length];
                 /***************************************
                  * 1~ 4. finalRecvBbytes 로부터 데이터 읽는다.
                  * *************************************/
                 for( int i=0; i<4 ; i++) type[i] = finalRecvBytes[i];
                 for( int i=0; i<128; i++ ) date[i] = finalRecvBytes[i+4];
                 for( int i=0; i<128; i++ ) macaddress[i] = finalRecvBytes[i+4+128];
                 for( int i=0; i<1024; i++ ) blank[i] = finalRecvBytes[i+4+128+128];
                 for( int i=0; i< finalRecvBytes.length - (4+128+128+1024) ; i++) data[i] = finalRecvBytes[i + 4+128+128+1024];

                 for(int i=0; i<type.length ; i++) System.out.print((char)type[i]);
                 System.out.println();
                 for(int i=0; i<date.length ; i++) System.out.print((char)date[i]);
                 System.out.println();
                 for(int i=0; i<macaddress.length ; i++) System.out.print((char)macaddress[i]);
                 System.out.println();
                 for(int i=0; i<blank.length ; i++) System.out.print((char)blank[i]);
                 System.out.println();
                /* for(int i=0; i<data.length ; i++) System.out.print((char)data[i]);
                 System.out.println();
                */

                String headerStr = new String(type).trim();
                String dateStr = new String(date).trim();
                String macaddressStr = new String(macaddress).trim();
                String blankStr = new String(blank).trim();
               // String dataStr = new String(data).trim();

                 System.out.println(headerStr);
                 System.out.println(dateStr);
                 System.out.println(macaddressStr);
                 System.out.println(blankStr);
                 //System.out.println(data);

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

}
