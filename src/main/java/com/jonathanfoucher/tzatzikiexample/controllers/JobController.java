package com.jonathanfoucher.tzatzikiexample.controllers;

import com.jonathanfoucher.tzatzikiexample.data.dto.JobDto;
import com.jonathanfoucher.tzatzikiexample.services.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    @GetMapping
    public List<JobDto> getAllJobs() {
        return jobService.getAllJobs();
    }

    @PostMapping
    public Long createJob(@RequestParam String name) {
        return jobService.createJob(name);
    }
}
