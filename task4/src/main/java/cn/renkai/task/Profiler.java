package cn.renkai.task;

import java.util.concurrent.TimeUnit;

/**
 * Created by renkai on 2017/2/18.
 */
public class Profiler {
    private static final ThreadLocal<Long> TIME_THREADlOCAL = new ThreadLocal<Long>(){
        protected  Long initialValue(){
            return System.currentTimeMillis();
        }
    };

    public static final void begin(){
        TIME_THREADlOCAL.set(System.currentTimeMillis());
    }

    public static final long end(){
        return System.currentTimeMillis() - TIME_THREADlOCAL.get();
    }

    public static void main(String[] args) throws InterruptedException {
        Profiler.begin();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(" Cast " + Profiler.end() + " mills ");
    }
}
