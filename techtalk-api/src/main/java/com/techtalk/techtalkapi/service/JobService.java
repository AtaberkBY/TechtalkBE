package com.techtalk.techtalkapi.service;

import com.techtalk.techtalkapi.data.JobRepository;
import com.techtalk.techtalkapi.domain.model.Job;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;

    public List<Job> getAllJobs() {
        log.info("GetAllJobs started");
        try {
            return jobRepository.findAll();
        } catch (Exception ex){
            log.error("GetAllJobs failed error: {}", ex.getMessage());
            return null;
        }
    }
}
