package cn.renkai.task;

/**
 * Created by renkai on 2017/2/17.
 */
public class Synchronized {
    public static void main(String[] args) {
        synchronized (Synchronized.class){}
    }
    public static synchronized void m(){}
}
