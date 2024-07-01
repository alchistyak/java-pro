package pro.java;

import pro.java.pool.MyThreadPool;

public class MainApplication {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        MyThreadPool pool = new MyThreadPool(10);

        for (int i = 0; i < 100; i++) {
            final int n = i + 1;
            pool.execute(() -> System.out.println("\t\t\tExecuting task " + n));
            Thread.sleep(5L);
        }

        pool.shutdown();

        try {
            pool.execute(() -> System.out.println("Pull task after shutdown"));
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        while (!pool.awaitTermination()) {
            System.out.println("Waiting tasks is down...");
            Thread.sleep(2000L);
        }
        System.out.println("Executig time is, ms: " + (System.currentTimeMillis() - startTime));
    }
}
