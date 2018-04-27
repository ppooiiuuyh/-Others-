package network;

/**
 * Created by ppooi on 2017-04-30.
 */

/********************************************************************
 * 설정해준 interval 보다 ruunable로 만들어진 thread처리가 일찍 끝나면 interval시간에 모자란만큼 기다렸다가 종료한다.
 *
* *******************************************************************/
public class MyIntervaledThread extends Thread {
    Runnable runnable;
    long interval;

    public MyIntervaledThread(){
        super();
    }
    public MyIntervaledThread (Runnable runnable,long interval){
        super();
        this.runnable = runnable;
        this.interval = interval;
    }

    public void run(){
        long start = System.currentTimeMillis();

        runnable.run();


        while(System.currentTimeMillis() - start < interval){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
