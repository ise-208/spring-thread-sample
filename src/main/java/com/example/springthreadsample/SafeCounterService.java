package com.example.springthreadsample;

import org.springframework.stereotype.Service;

@Service
public class SafeCounterService {
    private int count = 0;

    public synchronized void increment(int repeat) {
        for (int i = 0; i < repeat; i++) {
            count++;
        }
    }

    public synchronized int getIncrementCount() {
        return count;
    }
}
