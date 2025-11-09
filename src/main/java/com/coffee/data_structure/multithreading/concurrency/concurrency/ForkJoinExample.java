package com.coffee.data_structure.multithreading.concurrency.concurrency;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

class SumTask extends RecursiveTask<Long> {
    private final long[] numbers;
    private final int start, end;
    private static final int THRESHOLD = 1000;

    SumTask(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        if (end - start <= THRESHOLD) {
            long sum = 0;
            for (int i = start; i < end; i++) sum += numbers[i];
            return sum;
        }

        int mid = (start + end) / 2;
        SumTask left = new SumTask(numbers, start, mid);
        SumTask right = new SumTask(numbers, mid, end);

        left.fork();
        long rightResult = right.compute();
        long leftResult = left.join();

        return leftResult + rightResult;
    }
}

public class ForkJoinExample {

    public static void main(String[] args) {
        long[] numbers = new long[10_000];
        for (int i = 0; i < numbers.length; i++) numbers[i] = i;

        ForkJoinPool pool = new ForkJoinPool();
        long result = pool.invoke(new SumTask(numbers, 0, numbers.length));

        System.out.println("Sum = " + result);
    }
}
