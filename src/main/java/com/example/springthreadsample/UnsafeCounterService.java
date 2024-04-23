package com.example.springthreadsample;

import org.springframework.stereotype.Service;

@Service
public class UnsafeCounterService {
    private int count = 0;

    public void increment(int repeat) {
        for (int i = 0; i < repeat; i++) {
            count++;
        }
    }

    public int getIncrementCount() {
        return count;
    }
}
