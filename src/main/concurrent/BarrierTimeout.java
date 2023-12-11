package main.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Mechanism used by the Threads snake and ThreadPool workers
 * to wait a certain time after the game starts to also start
 *
 */

public class BarrierTimeout {

    private long timeout = -1; // If Timeout was not initialized, will wait permanently.
    Lock lock = new ReentrantLock();
    Condition notWaiting = lock.newCondition();
    private static List<BarrierTimeout> list = new ArrayList<>();

    public static BarrierTimeout getInstance() {
        return getInstance(0);
    }

    public static BarrierTimeout getInstance(int index) {
        if (list.size() == 0)
            list.add(new BarrierTimeout());
        return list.get(index);
    }

    private static int createBarrier() {
        list.add(new BarrierTimeout());
        return list.size() - 1;
    }

    public static BarrierTimeout createAndGetBarrier() {
        return getInstance(createBarrier());
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
        startTimeout();
    }

    public void waitForTimeout() {
        lock.lock();
        try {
            while (timeout != 0)
                notWaiting.await();
        } catch (InterruptedException e) {
        } finally {
            lock.unlock();
        }
    }

    private void startTimeout() {
        new Thread(() -> {
            lock.lock();
            try {
                Thread.sleep(timeout);
                timeout = 0;
                notWaiting.signalAll();
            } catch (InterruptedException e) {
            } finally {
                lock.unlock();
            }
        }).start();
    }
}
