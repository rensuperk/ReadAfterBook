package cn.renkai.task;

/**
 * Created by renkai on 2017/1/24.
 */
public class ConcurrencyTest {
    public static void main(String[] args) throws InterruptedException {
        long count=1000000000;
        concurrency(count);
        serial(count);
    }
    private static void concurrency(final long count) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable() {
            public void run() {
                int a = 0;
                for (long i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        thread.start();
        int b=0;
        for (long i=0;i<count;i++){
            b--;
        }
        thread.join();
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency:"+time+"ms,b="+b);

    }
    private static void  serial(final long count){
        long start = System.currentTimeMillis();
        int a=0;
        for (long i = 0; i < count; i++) {
            a += 5;
        }
        int b=0;
        for (long i=0;i<count;i++){
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency:"+time+"ms,b="+b);

    }
}
