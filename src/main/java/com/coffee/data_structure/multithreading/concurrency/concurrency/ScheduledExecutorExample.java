package com.coffee.data_structure.multithreading.concurrency.concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorExample {

    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        Runnable task = () -> System.out.println("Running scheduled task at " + System.currentTimeMillis());

        // Initial delay of 1s, repeat every 2s
        scheduler.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);

        // Stop after 10 seconds
        scheduler.schedule(() -> {
            System.out.println("Shutting down scheduler...");
            scheduler.shutdown();
        }, 10, TimeUnit.SECONDS);
    }
}
