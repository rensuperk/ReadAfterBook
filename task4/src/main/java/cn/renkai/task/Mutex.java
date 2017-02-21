package cn.renkai.task;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by renkai on 2017/2/20.
 */
public class Mutex implements Lock {
    private final  Sync sync = new Sync();
    public void lock() {
        sync.acquire(1);
    }
    public boolean isLock(){
        return sync.isHeldExclusively();
    }
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    public void unlock() {
        sync.release(0);
    }

    public Condition newCondition() {
        return sync.newCondition();
    }

    private static class Sync extends AbstractQueuedSynchronizer{
        protected boolean isHeldExclusively(){
            return getState() ==1;
        }
        public boolean tryAcquire(int acquire){
            if(compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
            }
            return true;
        }
        protected boolean tryRelease(int releases){
            if(getState() ==0){
                setExclusiveOwnerThread(null);
                setState(0);
            }
            return true;
        }
        Condition newCondition(){
            return new ConditionObject();
        }

    }


}
