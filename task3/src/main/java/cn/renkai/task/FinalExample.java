package cn.renkai.task;

/**
 * Created by renkai on 2017/2/11.
 */
public class FinalExample {
    int i;                                      //普通变量
    final int j;                                //final变量
    static FinalExample obj;

    public FinalExample() {

        j = 2;//写final域
//        System.out.println("写入j=" + 2);
        i = 1;//写普通域
//        System.out.println("写入i=" + 1);
    }
    public static void writer(){             //写线程A执行
        obj = new FinalExample();
    }
    public static void reader(){//读线程B执行
        FinalExample object = obj;//读对象引用
        int b = object.j;//读final域
        System.out.println("读取b=" + b);
        int a = object.i;//读普通域
        System.out.println("读取a=" + a );

    }

    public static void main(String[] args) throws InterruptedException {
        Thread A = new Thread(new Runnable() {
            public void run() {
                FinalExample.writer();
            }
        });
        Thread B = new Thread(new Runnable() {
            public void run() {
                FinalExample.reader();
            }
        });
        B.start();
        A.start();
    }
}
