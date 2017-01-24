package cn.renkai.task;

import javax.sound.midi.Soundbank;

/**
 * Created by renkai on 2017/1/24.
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        new DeadLockDemo().deadlock();
    }

    private static String A="A";
    private static String B="B";
    private void deadlock(){
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                synchronized (A) {
                    try {
                        Thread.currentThread().wait(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B) {
                        System.out.println("1");
                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                synchronized (B) {
                    synchronized (A) {
                        System.out.println("2");
                    }
                }
            }
        });
        thread1.start();
        thread2.start();
    }
}
