package cn.renkai.task;

/**
 * Created by renkai on 2017/2/17.
 */
public class Deamon {
    public static void main(String[] args) {
        Thread deammonRunner = new Thread(new DeammonRunner(), "DeammonRunner");
        deammonRunner.setDaemon(true);
        deammonRunner.start();
    }
    static class DeammonRunner implements Runnable{
        public void run() {
            try {
                SleepUtils.second(100);
            }finally {
                System.out.println("deamonthread finally run.");
            }
        }
    }
}
