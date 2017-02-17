package cn.renkai.task;

/**
 * Created by renkai on 2017/2/17.
 */
public class SafeLoayInitialization {
        private  static Instance instance;

    public synchronized static Instance getInstance(){
        if(instance ==null){
            instance = new Instance();
        }
        return instance;
    }
}
