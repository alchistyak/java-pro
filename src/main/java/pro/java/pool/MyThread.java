package pro.java.pool;

import java.io.Closeable;
import java.io.IOException;
import java.util.LinkedList;

public class MyThread implements Runnable, Closeable {
    private final MyThreadPool myThreadPool;
    private LinkedList<Runnable> list = new LinkedList<>();
    private volatile boolean toInterrupt = false;

    public MyThread(MyThreadPool myThreadPool, LinkedList<Runnable> list) {
        this.myThreadPool = myThreadPool;
        this.list = list;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        try {
            while (!toInterrupt) {
                if (list.size() > 0) {
                    System.out.println("\t" + Thread.currentThread().getName() + " -> tasks to execute " + list.size());
                    synchronized (list) {
                        if (list.size() > 0) {
                            System.out.println("\t\t" + Thread.currentThread().getName() + " -> execute task");
                            Runnable r = list.removeFirst();
                            if (r != null) {
                                r.run();
                            }
                        }
                    }
                } else {
                    if (myThreadPool.isShutdown()) {
                        toInterrupt = true;
                    }
                }
                Thread.sleep(200L);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws IOException {
        toInterrupt = true;
    }
}
