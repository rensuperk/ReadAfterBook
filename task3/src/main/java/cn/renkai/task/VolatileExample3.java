package cn.renkai.task;

/**
 * Created by renkai on 2017/2/3.
 */
public class VolatileExample3 {
    long a=0l;
    volatile boolean flag = false;
    public void  writer() {
        a=1;
        flag=true;
    }

    public void reader() {
        if(flag){
            long i=this.a;
        }
    }

}
