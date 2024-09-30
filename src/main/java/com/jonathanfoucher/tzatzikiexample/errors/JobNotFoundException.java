package com.jonathanfoucher.tzatzikiexample.errors;

public class JobNotFoundException extends RuntimeException {
    public JobNotFoundException(Long id) {
        super("Job not found for id=" + id);
    }
}
