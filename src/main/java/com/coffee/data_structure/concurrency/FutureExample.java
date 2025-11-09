package com.coffee.data_structure.concurrency;

import java.util.concurrent.*;

public class FutureExample {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Future<Integer> result = executor.submit(() -> {
            System.out.println("Processing in background...");
            Thread.sleep(1000);
            return 42;
        });

        System.out.println("Main thread doing other work...");
        System.out.println("Result from Future: " + result.get());

        executor.shutdown();
    }
}
