package com.example.springthreadsample;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThreadTest {
    int repeat = 100;
    int thread = 10;

    @Test
    void Unsafeのスレッドを呼び出させてテストを失敗させる() throws InterruptedException {
        UnsafeCounterService unsafeCounterService = new UnsafeCounterService();
        ExecutorService executorService = Executors.newFixedThreadPool(thread);
        AtomicInteger successIncrement = new AtomicInteger();

        for (int i = 0; i < thread; i++) {
            executorService.submit(() -> {
                unsafeCounterService.increment(repeat);
                for (int j = 0; j < repeat; j++) {
                    successIncrement.incrementAndGet();
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println(thread);
        System.out.println(unsafeCounterService.getIncrementCount());
        System.out.println(successIncrement.get());

        assertEquals(unsafeCounterService.getIncrementCount(), successIncrement.get());

    }

    @Test
    void Safeのスレッドを呼び出させてテストを成功させる() throws InterruptedException {
        SafeCounterService safeCounterService = new SafeCounterService();
        ExecutorService executorService = Executors.newFixedThreadPool(thread);
        AtomicInteger successIncrement = new AtomicInteger();

        for (int i = 0; i < thread; i++) {
            executorService.submit(() -> {
                safeCounterService.increment(repeat);
                for (int j = 0; j < repeat; j++) {
                    successIncrement.incrementAndGet();
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println(thread);
        System.out.println(safeCounterService.getIncrementCount());
        System.out.println(successIncrement.get());

        assertEquals(safeCounterService.getIncrementCount(), successIncrement.get());

    }
}
