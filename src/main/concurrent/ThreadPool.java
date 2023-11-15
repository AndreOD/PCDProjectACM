package main.concurrent;

import main.game.ObstacleMover;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {

    private BlockingQueue<Runnable> blockingQueue;

    private List<WorkerThread> workers = new ArrayList<>();

    private boolean stoped = false;



    /***
     * Construtor that receives Runnable List used to create a new BlockingQueue
     * Usefull for pre-prepared list of tasks
     * Used in LocalBoard init()
     * @param tasks
     * @param numberOfWorkers
     */

    public ThreadPool(List<Runnable> tasks, int numberOfWorkers) {
        blockingQueue = new LinkedBlockingQueue<>();
        for(Runnable task : tasks)
            blockingQueue.add(task);

        for (int i = 0 ; i < numberOfWorkers ; i++){
            WorkerThread worker = new WorkerThread();
            workers.add(worker);
            worker.start();
        }
    }

    public ThreadPool(int numberOfWorkers) {
        blockingQueue = new LinkedBlockingQueue<>();

        for (int i = 0 ; i < numberOfWorkers ; i++){
            WorkerThread worker = new WorkerThread();
            workers.add(worker);
            worker.start();
        }
    }

    public void interruptAll(){
        stoped = true;
        for (WorkerThread worker : workers){ worker.interrupt();}
    }


    public void submit(Runnable runnable){
        blockingQueue.add(runnable);

    }


    class WorkerThread extends Thread implements Executor {


        @Override
        public void run() {
            try {
                while (!stoped) {
                    Runnable runnable = blockingQueue.take();
                    execute(runnable);
                }
            } catch (InterruptedException e) { }
        }

        @Override
        public void execute(Runnable runnable)   {
            Thread task = new Thread(runnable);
            task.start();
            try {
                task.join();
            } catch (InterruptedException e) {
                task.interrupt();
            }
        }
    }


}
