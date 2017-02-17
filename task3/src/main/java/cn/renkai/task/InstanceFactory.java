package cn.renkai.task;

/**
 * Created by renkai on 2017/2/17.
 */
public class InstanceFactory {
    private static class InstanceHolder{
        public static Instance instance = new Instance();
    }
    public static  Instance getInstance(){
        return InstanceHolder.instance;
    }
}
