package com.jonathanfoucher.tzatzikiexample.data.repository;

import com.jonathanfoucher.tzatzikiexample.data.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}
