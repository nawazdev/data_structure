package com.coffee.data_structure.multithreading.synchronization;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainApp {
    private static final int NUM_THREADS = 5;
    private static final int NUM_INCREMENTS = 3;

    public static void main(String[] args) throws InterruptedException {
        
        System.out.println("--- Testing Synchronized Methods ---");
        testSynchronizedMethods();

        // Separate the output
        System.out.println("\n--- Testing ReentrantLock ---");
        testReentrantLock();
    }

    private static void testSynchronizedMethods() throws InterruptedException {
        SynchronizedMethods syncObj = new SynchronizedMethods();
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        // Submit tasks to run the synchronized method
        for (int i = 0; i < NUM_THREADS * NUM_INCREMENTS; i++) {
            executor.submit(() -> syncObj.incrementMethod());
            // Alternatively, test the synchronized block:
            // executor.submit(() -> syncObj.incrementBlock());
        }

        // Shutdown the executor and wait for tasks to finish
        shutdownAndAwaitTermination(executor);
    }

    private static void testReentrantLock() throws InterruptedException {
        ReentrantLockExample lockObj = new ReentrantLockExample();
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);

        // Submit tasks to run the ReentrantLock method
        for (int i = 0; i < NUM_THREADS * NUM_INCREMENTS; i++) {
            executor.submit(() -> lockObj.increment());
        }

        // Shutdown the executor and wait for tasks to finish
        shutdownAndAwaitTermination(executor);
    }
    
    // Utility method to safely shut down the ExecutorService
    private static void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(10, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(10, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }
}