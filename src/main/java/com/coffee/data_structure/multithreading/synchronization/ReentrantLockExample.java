package com.coffee.data_structure.multithreading.synchronization;// ReentrantLockExample.java
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {
    // 1. Explicitly create the lock object
    private final ReentrantLock lock = new ReentrantLock();
    private int counter = 0;

    public void increment() {
        // 2. Explicitly acquire the lock
        lock.lock(); 
        
        try {
            // Critical Section
            counter++;
            System.out.println(Thread.currentThread().getName() + " incremented to: " + counter);
        } finally {
            // 3. MUST release the lock in a finally block to prevent deadlocks
            lock.unlock(); 
        }
    }
}