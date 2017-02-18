package cn.renkai.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by renkai on 2017/2/18.
 */
public class WaitNotify {
    static boolean flag = true;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread wait = new Thread(new Wait(), "Wait");
        wait.start();
        TimeUnit.SECONDS.sleep(1);
        Thread notify = new Thread(new Notify(), "Notify");
        notify.start();
    }

    static class Wait implements Runnable{
        public void run() {
            synchronized (lock){
                while (flag){

                    try {
                        System.out.println(Thread.currentThread() + " flag is true. wait @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread() + " flag is false. running @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }

    static class Notify implements  Runnable{
        public void run() {
            synchronized (lock){
                System.out.println(Thread.currentThread() + " hold lock. notify @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                SleepUtils.second(5);
            }
            synchronized (lock){
                System.out.println(Thread.currentThread() + " hold lock again. sleep @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                SleepUtils.second(5);

            }
        }
    }
}
