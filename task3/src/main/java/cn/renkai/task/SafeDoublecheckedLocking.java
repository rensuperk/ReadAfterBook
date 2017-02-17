package cn.renkai.task;

/**
 * Created by renkai on 2017/2/17.
 */
public class SafeDoublecheckedLocking {

    private volatile   static Instance instance;

    public  static Instance getInstance(){
        if(instance ==null){
            synchronized(Instance.class){
                if(instance == null){

                    instance = new Instance();
                }
            }
        }
        return instance;
    }
}
