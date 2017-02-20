package cn.renkai.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by renkai on 2017/2/18.
 */
public interface ThreadPool<Job extends  Runnable> {
    void execute(Job job);
    void shutdown();
    void addWorkers(int num);
    void removeWorker(int num);
    int getJobSize();

}

class DefaultThreadPool<Job extends  Runnable> implements ThreadPool<Job> {
    private static final int MAX_WORKER_NUMBERS = 10;
    private static final int DEFAULT_WORKER_NUMBERS = 5;
    private static final int MIN_WORKER_NUMBERS = 1;
    private final LinkedList<Job> jobs = new LinkedList<Job>();
    private final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());
    private int workerNum = DEFAULT_WORKER_NUMBERS;
    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool() {
        initializeWorkers(DEFAULT_WORKER_NUMBERS);
    }

    public DefaultThreadPool(int num) {
        this.workerNum = num >MAX_WORKER_NUMBERS ?MAX_WORKER_NUMBERS :num <MIN_WORKER_NUMBERS ?MIN_WORKER_NUMBERS:num;
        initializeWorkers(workerNum);
    }
    private void initializeWorkers(int num){
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "ThreadPool-worker-" + threadNum.incrementAndGet());
            thread.start();
        }
    }


    public void execute(Job job) {
        if(job != null){
            synchronized (job){
                jobs.add(job);
                jobs.notify();
            }
        }
    }

    public void shutdown() {
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    public void addWorkers(int num) {
        synchronized (jobs){
            if( num +this.workerNum >MAX_WORKER_NUMBERS){
                num = MAX_WORKER_NUMBERS - this.workerNum;
            }
            initializeWorkers(num);
            this.workerNum += num;
        }
    }

    public void removeWorker(int num) {
        synchronized (jobs){
            if(num >= this.workerNum){
                throw new IllegalArgumentException("beyong workNum");
            }
            int count  =0;
            while (count<num){
                Worker worker = workers.get(count);
                if(workers.remove(worker)){
                    worker.shutdown();
                    count++;
                }
            }
            this.workerNum -=count;
        }
    }

    public int getJobSize() {
        return jobs.size();
    }
    public class Worker implements Runnable {
        private volatile boolean running = true;
        public void run() {
            while (running){
                Job job = null;
                synchronized (jobs){
                    while (jobs.isEmpty()){
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    job = jobs.removeFirst();
                }
                if(job != null){
                    job.run();
                }
            }

        }
        public void shutdown(){
            running = true;
        }
    }
}
