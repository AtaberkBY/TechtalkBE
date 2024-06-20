package com.techtalk.techtalkapi.controller;

import com.techtalk.techtalkapi.application.jobcreate.CreateJobRequest;
import com.techtalk.techtalkapi.application.jobcreate.CreateJobResult;
import com.techtalk.techtalkapi.domain.model.Job;
import com.techtalk.techtalkapi.service.JobService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public CreateJobResult createJob(@RequestBody CreateJobRequest createJobRequest) {
        return jobService.create(createJobRequest);
    }
}
