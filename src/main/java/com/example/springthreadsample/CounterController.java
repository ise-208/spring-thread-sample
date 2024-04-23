package com.example.springthreadsample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterController {
    private SafeCounterService safeCounterService;
    private UnsafeCounterService unsafeCounterService;

    public CounterController(SafeCounterService safeCounterService, UnsafeCounterService unsafeCounterService) {
        this.safeCounterService = safeCounterService;
        this.unsafeCounterService = unsafeCounterService;
    }

    @GetMapping("/safe")
    public String safe() {
        return "safe: " + safeCounterService.getIncrementCount();
    }

    @GetMapping("/unsafe")
    public String unsafe() {
        return "unsafe: " + unsafeCounterService.getIncrementCount();
    }
}
