package cn.renkai.task;

import java.util.concurrent.TimeUnit;

/**
 * Created by renkai on 2017/2/17.
 */
public class Interrupted {
    public static void main(String[] args) throws InterruptedException {
        Thread busyRunner = new Thread(new BusyRunner(), "BusyRunner");
        busyRunner.setDaemon(true);
        Thread sleepRunner = new Thread(new SleepRunner(), "SleepRunner");
        sleepRunner.setDaemon(true);
        busyRunner.start();
        sleepRunner.start();
        TimeUnit.SECONDS.sleep(5);
        sleepRunner.interrupt();
        busyRunner.interrupt();
        System.out.println("sleepRunner = " + sleepRunner.isInterrupted());
        System.out.println("busyRunner = " + busyRunner.isInterrupted());
        SleepUtils.second(2);
    }
    static class SleepRunner implements  Runnable{

        public void run() {
            SleepUtils.second(10);
        }
    }
    static class BusyRunner implements Runnable{

        public void run() {
            while (true){

            }
        }
    }
}
