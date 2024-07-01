package pro.java.pool;

import java.util.LinkedList;

public class MyThreadPool {
    private final int corePoolSize;
    private LinkedList<Runnable> list = new LinkedList<>();
    private Thread[] threads;
    private volatile boolean shutdown = false;

    public boolean isShutdown() {
        return shutdown;
    }

    public MyThreadPool(int corePoolSize) throws InterruptedException {
        this.corePoolSize = corePoolSize;
        threads = new Thread[corePoolSize];

        for (int i = 0; i < corePoolSize; i++) {
            threads[i] = new Thread(new MyThread(this, list));
            threads[i].start();
            Thread.sleep(100L);
        }
    }

    public void execute(Runnable r) {
        if (!shutdown) {
            list.add(r);
        } else {
            throw new IllegalStateException("Pool is already shutdown");
        }
    }

    public void shutdown() {
        this.shutdown = true;
    }

    public boolean awaitTermination() {
        if (list.size() > 0) {
            return false;
        }
        return true;
    }
}
