package cn.renkai.task;

/**
 * Created by renkai on 2017/2/16.
 */
public class FinalReferenceEscapeExample {
    final int i;
    static FinalReferenceEscapeExample obj;
    public FinalReferenceEscapeExample(){
        i=1;                                    //写final域
        obj=this;                               //this引用在此溢出
    }
    public static void writer(){
        new FinalReferenceEscapeExample();
    }
    public static void reader(){
        if(obj != null){                         //3
            int temp = obj.i;                      //4
        }
    }
}
