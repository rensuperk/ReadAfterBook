package cn.renkai.task;

import java.util.concurrent.TimeUnit;

/**
 * Created by renkai on 2017/2/17.
 */
public class SleepUtils{
    public static final void second(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
