package cn.renkai.task;

import com.sun.xml.internal.bind.v2.runtime.output.XmlOutput;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by renkai on 2017/2/21.
 */
public class FairAndUnfairTest {
    private static Lock  failLock = new ReentrantLock2(true);
    private static Lock unfailLock = new ReentrantLock2(false);
    @Test
    public void fair(){
        testLock(failLock);
    }
    @Test
    public void unfair(){
        testLock(unfailLock);
    }

    private void testLock(Lock lock){
        //启动5个job
        for (int i = 0; i < 5; i++) {
            Job job = new Job(lock,String.valueOf(i));
            job.setDaemon(true);
            job.start();
        }
    }
    private static class Job extends Thread{
        private Lock lock;
        public Job(Lock lock,String name){
            this.lock = lock;
            super.setName(name);
        }
        public void run(){
            ReentrantLock2 lock2 = (ReentrantLock2) lock;
            lock2.lock();
                try {
                    for (int i = 0; i < 2; i++) {

                        System.out.print(Thread.currentThread().getName()+"--");
                        for (Thread thread : lock2.getQueuedThreads()) {
                            System.out.print(thread.getName()+",");
                        }
                        System.out.println();
                    }
//                    SleepUtils.second(1);
//                    SleepUtils.second(1);
                }finally {
                    lock2.unlock();
                }

        }
    }
    private static class ReentrantLock2 extends ReentrantLock{
        public ReentrantLock2(boolean fair){
            super(fair);
        }
        public Collection<Thread> getQueuedThreads(){
            List<Thread> arrayList = new ArrayList<Thread>(super.getQueuedThreads());
            Collections.reverse(arrayList);
            return arrayList;
        }
    }
}
