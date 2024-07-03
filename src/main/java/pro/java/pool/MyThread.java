package pro.java.pool;

import java.io.Closeable;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;

public class MyThread implements Runnable, Closeable {
    private final MyThreadPool myThreadPool;
    private LinkedList<Runnable> listTasksToRun = new LinkedList<>();
    private volatile boolean toInterrupt = false;

    public MyThread(MyThreadPool myThreadPool, LinkedList<Runnable> listTasksToRun) {
        this.myThreadPool = myThreadPool;
        this.listTasksToRun = listTasksToRun;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        final Lock lock = myThreadPool.getLock();
        try {
            while (!toInterrupt) {
                lock.lock();
                if (listTasksToRun.size() > 0) {
                    System.out.println("\t" + Thread.currentThread().getName() + " -> tasks to execute " + listTasksToRun.size());
                    System.out.println("\t\t -> execute task");
                    Runnable r = listTasksToRun.removeFirst();
                    if (r != null) {
                        r.run();
                    }
                } else {
                    if (myThreadPool.isShutdown()) {
                        toInterrupt = true;
                    }
                }
                lock.unlock();
                Thread.sleep(200L);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                lock.unlock();
            } catch (IllegalMonitorStateException illegalMonitorStateException) {
            }
        }
    }

    @Override
    public void close() throws IOException {
        toInterrupt = true;
    }
}
