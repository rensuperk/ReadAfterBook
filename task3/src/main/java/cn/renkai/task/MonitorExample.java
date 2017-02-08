package cn.renkai.task;

/**
 * Created by renkai on 2017/2/8.
 */
public class MonitorExample {
    int a=0;
    public synchronized void writer(){
        a++;
    }
    public synchronized void reader(){
        int i=a;
    }
}
