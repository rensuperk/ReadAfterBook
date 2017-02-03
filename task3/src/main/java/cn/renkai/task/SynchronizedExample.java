package cn.renkai.task;

/**
 * Created by renkai on 2017/2/3.
 */
    public class SynchronizedExample {
        public void   check(final SynchronizedExample synchronizedExample){
    //        final ReorderExample reorderExample=new ReorderExample();
            Thread thread1 = new Thread(new Runnable() {
                public void run() {
                    synchronizedExample.write();
    //                reorderExample.read();
                }
            });
            Thread thread2 = new Thread(new Runnable() {
                public void run() {
                    synchronizedExample.read();
    //                reorderExample.write();
                }
            });
            thread1.start();
            thread2.start();
        }
        public static void main(String[] args) {
            final SynchronizedExample synchronizedExample = new SynchronizedExample();

            for (int i = 0; i < 1000; i++) {
                synchronizedExample.check(synchronizedExample);
            }
        }
        int a = 0;
        boolean flag = false;
        public synchronized void write(){
            a=1;
            flag=true;
        }
        public synchronized void read(){
            if(flag){
                int i=a*a;
                System.out.println(true);
            }else{
                System.out.println(false);
            }
        }
    }
