package cn.renkai.task;

/**
 * Created by renkai on 2017/1/24.
 * 验证非线程同步的读写没有一致性的问题
 */
public class ReorderExample {
    int a=0;
    boolean flag=false;
    public void write(){
        a=1;
        flag=true;
    }
    public void read(){
        if(flag){
            int i=a*a;
            System.out.println(true);
        }else{
            System.out.println(false);
        }
    }

    public void   check(){
        final ReorderExample reorderExample=new ReorderExample();
        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                reorderExample.write();
//                reorderExample.read();
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                reorderExample.read();
//                reorderExample.write();
            }
        });
        thread1.start();
        thread2.start();
    }
    public static void main(String[] args) {
        final ReorderExample reorderExample = new ReorderExample();

        for (int i = 0; i < 1000; i++) {
            reorderExample.check();
        }
    }
}
