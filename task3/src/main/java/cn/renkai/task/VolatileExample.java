package cn.renkai.task;

/**
 * Created by renkai on 2017/2/3.
 */
public class VolatileExample {
    volatile long vl=0l;

    public long getVl() {
        System.out.println("getVl==="+vl);
        return vl;
    }

    public void setVl(long vl) {
        this.vl = vl;
        System.out.println("setVl==="+vl);
    }
    public void getAndIncrement(){
        vl++;
        System.out.println("getAndIncrement==="+vl);
    }

}
