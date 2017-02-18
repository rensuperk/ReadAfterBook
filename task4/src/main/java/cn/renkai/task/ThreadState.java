package cn.renkai.task;

import java.util.concurrent.TimeUnit;

/**
 * Created by renkai on 2017/2/17.
 */
public class ThreadState {
    public static void main(String[] args) {
        new Thread(new TimeWaiting(),"TimeWaiting").start();
        new Thread(new Waiting(),"Waiting").start();
        new Thread(new Blocked(),"Blocked-1").start();
        new Thread(new Blocked(),"Blocked-2").start();
    }
    static class TimeWaiting implements Runnable{

        public void run() {
            while (true){
                SleepUtils.second(100);
            }
        }
    }
    static class Waiting implements Runnable{

        public void run() {
            while (true){
                synchronized (Waiting.class){
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    static class Blocked implements Runnable{

        public void run() {
            synchronized (Blocked.class){
                while (true){
                    SleepUtils.second(100);
                }
            }
        }
    }

}
