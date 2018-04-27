import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by ppooi on 2017-05-05.
 */
public class Test {
    public static void main(String[] argv) {


  /*      byte[] temp = new byte[14];
        temp[0] = '3';
        temp[1] = '2';
        temp[2] = 0;
        String str = new String(temp).trim();

        System.out.println(Integer.parseInt(str));
*/

 /*   Thread thread = new Thread1();
    thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    */

  /*      try {
            System.out.println(InetAddress.getLocalHost());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }*/


    }



}











class Thread1 extends Thread{
    public void run(){
        Thread timeout = new Thread2(this);
        timeout.start();

        int a=0;
        while(a>1000000000){
            a++;
            int b =0;
            {
                while(b>1000000000){
                    b++;
                }
            }
        }
            System.out.println("is alive");


    }
}

class Thread2  extends  Thread{
    Thread upper;
    public Thread2(Thread t){
        upper = t;
    }

    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        while(upper.isAlive()) {
            upper.isInterrupted();
            System.out.println("timeout!");
        }
        while(true){
            System.out.println("thread2 still alive");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
