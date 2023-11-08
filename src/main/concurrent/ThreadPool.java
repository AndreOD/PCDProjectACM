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

    /***
     * Construtor that receives a blockingQueue
     *
     * @param blockingQueue
     * @param numberOfWorkers
     */


    public ThreadPool(BlockingQueue<Runnable> blockingQueue, int numberOfWorkers) {
        this.blockingQueue = blockingQueue;
        for (int i = 0 ; i < numberOfWorkers ; i++){
            WorkerThread worker = new WorkerThread();
            workers.add(worker);
            worker.start();
        }
    }

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



    class WorkerThread extends Thread implements Executor {
        @Override
        public void run() {
            try {
                while (!blockingQueue.isEmpty()) {
                    Runnable obstacleMover = blockingQueue.take();
                    execute(obstacleMover);
                }
            } catch (InterruptedException e) {
            }
        }

        @Override
        public void execute(Runnable command)  {
            Thread task = new Thread(command);
            task.start();
            try {
                task.join();
            } catch (InterruptedException e) {}
        }
    }


}
