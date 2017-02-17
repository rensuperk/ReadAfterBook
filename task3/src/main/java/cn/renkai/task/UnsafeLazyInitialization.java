package cn.renkai.task;


import sun.security.jca.GetInstance;

/**
 * Created by renkai on 2017/2/17.
 */
public class UnsafeLazyInitialization {
    private static Instance instance;

    public static Instance getInstance(){
        if(instance ==null){
            instance = new Instance();
        }
        return instance;
    }
}

