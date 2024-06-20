package com.techtalk.techtalkapi.controller;

import com.techtalk.techtalkapi.domain.model.Job;
import com.techtalk.techtalkapi.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/job")
@AllArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping
    public List<Job> jobs() {
        return jobService.getAllJobs();
    }
}
