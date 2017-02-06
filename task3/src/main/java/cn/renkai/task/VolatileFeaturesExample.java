package cn.renkai.task;

/**
 * Created by renkai on 2017/2/3.
 */
public class VolatileFeaturesExample {
    long vl=0l;

    public synchronized long getVl() {
        return vl;
    }

    public synchronized void setVl(long vl) {
        this.vl = vl;
    }
    public void getAndIncrement(){
        long temp = getVl();
        temp++;
        setVl(temp);
    }
}
