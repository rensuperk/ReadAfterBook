package cn.renkai.task;

import java.util.concurrent.TimeUnit;

/**
 * Created by renkai on 2017/2/17.
 */
public class ShutDown {
    public static void main(String[] args) throws InterruptedException {
        Runner one = new Runner();
        Thread countThread = new Thread(one, "CountThread");
        countThread.start();
        TimeUnit.SECONDS.sleep(1);
        countThread.interrupt();
        Runner two = new Runner();
        countThread = new Thread(two,"CountThread");
        countThread.start();
        TimeUnit.SECONDS.sleep(1);
        two.Cancel();
    }
    private static class Runner implements Runnable{
        private long i;
        private volatile boolean on = true;
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("Count i = " + i);
        }
        public void Cancel(){
            on = false;
        }
    }
}
