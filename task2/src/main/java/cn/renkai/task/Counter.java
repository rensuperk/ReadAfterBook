package cn.renkai.task;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by renkai on 2017/1/24.
 */
public class Counter {
    private AtomicInteger atomicI = new AtomicInteger(0);
    private int i=0;
    private void count(){
        i++;
    }
    private void saveCount(){
        for (;;){
            int i=atomicI.get();
            boolean suc = atomicI.compareAndSet(i, ++i);
            if(suc){
                break;
            }
        }
    }
    public static void main(String[] args) {
        final Counter cas = new Counter();
        ArrayList<Thread> threads = new ArrayList<Thread>(600);
        long start = System.currentTimeMillis();
        for (int j=0;j<100;j++){
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        cas.count();
                        cas.saveCount();
                    }
                }
            });
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(cas.i);
        System.out.println(cas.atomicI.get());
        System.out.println(System.currentTimeMillis()-start+"ms");
    }

}
