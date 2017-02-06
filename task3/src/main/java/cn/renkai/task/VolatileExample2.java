package cn.renkai.task;

import sun.awt.windows.ThemeReader;

/**
 * Created by renkai on 2017/2/3.
 */
public class VolatileExample2 {
    int a=0;
     boolean flag = false;
    public void writ(){
        a=1;
        flag=true;
    }
    public void read(){
        if(flag){
            int i = a;
            System.out.println(i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final VolatileExample2 volatileExample2 = new VolatileExample2();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                volatileExample2.read();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                volatileExample2.writ();
            }
        });
        t2.start();
        t1.start();
    }
}
