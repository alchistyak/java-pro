package pro.java.pool;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyThreadPool {
    private final int corePoolSize;
    private LinkedList<Runnable> listTasksToRun = new LinkedList<>();
    private Thread[] threads;

    public Lock getLock() {
        return lock;
    }

    private volatile boolean shutdown = false;
    private final Lock lock = new ReentrantLock();

    public boolean isShutdown() {
        return shutdown;
    }

    public MyThreadPool(int corePoolSize) throws InterruptedException {
        this.corePoolSize = corePoolSize;
        threads = new Thread[corePoolSize];

        for (int i = 0; i < corePoolSize; i++) {
            threads[i] = new Thread(new MyThread(this, listTasksToRun));
            threads[i].start();
            Thread.sleep(100L);
        }
    }

    public void execute(Runnable task) {
        if (!shutdown) {
            listTasksToRun.add(task);
        } else {
            throw new IllegalStateException("Pool is already shutdown");
        }
    }

    public void shutdown() {
        this.shutdown = true;
    }

    public boolean awaitTermination() {
        if (listTasksToRun.size() > 0) {
            return false;
        }
        return true;
    }
}
