package com.coffee.data_structure.multithreading.synchronization;

// SynchronizedMethods.java
public class SynchronizedMethods {
    private int counter = 0;

    // A. Synchronized Method: Locks the entire instance (this)
    public synchronized void incrementMethod() {
        // Critical Section
        counter++;
        System.out.println(Thread.currentThread().getName() + " incremented to: " + counter);
    }

    // B. Synchronized Block: Locks only the specific object (this)
    public void incrementBlock() {
        // Only lock the necessary section
        synchronized (this) {
            counter++;
            System.out.println(Thread.currentThread().getName() + " incremented to: " + counter);
        }
    }
}