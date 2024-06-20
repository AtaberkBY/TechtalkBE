package com.techtalk.techtalkapi.domain.assembler;

import com.techtalk.techtalkapi.application.jobcreate.CreateJobRequest;
import com.techtalk.techtalkapi.domain.model.Job;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class JobAssembler {
    public Job applyJobWithCreateRequest(CreateJobRequest request) {
        Job job = new Job();

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setCreatedDate(LocalDateTime.now());
        job.setContactEmail(request.getContact());
        job.setRequirements(request.getRequirements());
        job.setUsername(request.getUsername());

        if (!jobFieldsCheck(job)) {
            return null;
        }

        return job;
    }

    private boolean jobFieldsCheck(Job job) {
        return job.getTitle() != null &&
                job.getDescription() != null &&
                job.getCreatedDate() != null &&
                job.getContactEmail() != null &&
                job.getRequirements() != null &&
                job.getUsername() != null;
    }
}
