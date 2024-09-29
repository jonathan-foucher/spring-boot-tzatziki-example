package com.jonathanfoucher.tzatzikiexample.services;

import com.jonathanfoucher.tzatzikiexample.data.dto.JobDto;
import com.jonathanfoucher.tzatzikiexample.data.model.Job;
import com.jonathanfoucher.tzatzikiexample.data.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class JobService {
    private final JobRepository jobRepository;

    public List<JobDto> getAllJobs() {
        return jobRepository.findAll()
                .stream()
                .map(this::convertEntityToDto)
                .toList();
    }

    public Long createJob(String name) {
        Job job = createJobEntity(name);
        return jobRepository.save(job)
                .getId();
    }

    private JobDto convertEntityToDto(Job entity) {
        JobDto dto = new JobDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    private Job createJobEntity(String name) {
        Job entity = new Job();
        entity.setName(name);
        return entity;
    }
}
