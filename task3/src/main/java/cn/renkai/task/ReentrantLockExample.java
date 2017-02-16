package cn.renkai.task;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by renkai on 2017/2/10.
 */
public class ReentrantLockExample {
    int a=0;
    ReentrantLock lock = new ReentrantLock();
    public void writer(){
        lock.lock();
        try {
            a++;
        }finally {
            lock.unlock();
        }
    }
    public void reader(){
        lock.lock();
        try {
            int i=a;
            System.out.println(a);
        }finally {
            lock.unlock();
        }
    }

}
