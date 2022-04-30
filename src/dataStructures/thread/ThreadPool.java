package dataStructures.thread;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

// 手写线程池
public class ThreadPool {
    private static final int WORK_NUM = 5;

    private static final int TASK_NUM = 100;

    private final BlockingQueue<Runnable> taskQueue;

    private final Set<WorkThread> workThreads;

    private int workNumber;

    private int taskNumber;

    public ThreadPool(){
        this(WORK_NUM, TASK_NUM);
    }

    public ThreadPool(int workNumber, int taskNumber){
        if(taskNumber <= 0){
            taskNumber = TASK_NUM;
        }
        if(workNumber <= 0){
            workNumber = WORK_NUM;
        }
        this.taskQueue = new ArrayBlockingQueue<Runnable>(taskNumber);
        this.workNumber = workNumber;
        this.taskNumber = taskNumber;

        this.workThreads = new HashSet<>();

        for(int i = 0; i < workNumber; i++){
            WorkThread workThread = new WorkThread("thread_" + i);
            workThread.start();
            workThreads.add(workThread);
        }
    }

    public void execute(Runnable task){
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void destroy(){
        System.out.println("ready close pool...");
        for(WorkThread workThread : workThreads){
            workThread.stopWorker();
            workThread = null;
        }
        workThreads.clear();
    }

    private class WorkThread extends Thread{
        public WorkThread(String name){
            super();
            setName(name);
        }

        @Override
        public void run(){
            while (!interrupted()){
                try {
                    Runnable runnable = taskQueue.take();
                    if(runnable != null){
                        System.out.println(getName() + " ready exexute: " + runnable.toString());
                        runnable.run();
                    }
                    runnable = null;
                } catch (InterruptedException e) {
                    interrupted();
                    e.printStackTrace();
                }
            }
        }

        public void stopWorker(){
            interrupted();
        }
    }

}