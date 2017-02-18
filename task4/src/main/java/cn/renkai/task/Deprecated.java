package cn.renkai.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by renkai on 2017/2/17.
 */
public class Deprecated {
    public static void main(String[] args) throws InterruptedException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Thread runner = new Thread(new Runner(), "Runner");
        runner.setDaemon(true);
        runner.start();
        TimeUnit.SECONDS.sleep(3);
        runner.suspend();
        System.out.println(" suspend at " + simpleDateFormat.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
        runner.resume();
        System.out.println(" resume at " + simpleDateFormat.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
        runner.stop();
        System.out.println(" stop at " + simpleDateFormat.format(new Date()));
        TimeUnit.SECONDS.sleep(3);
    }
    static class Runner implements Runnable{
        public void run() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            while (true){
                System.out.println(Thread.currentThread().getName() + " Run at " + simpleDateFormat.format(new Date()));
                SleepUtils.second(1);
            }
        }
    }
}
