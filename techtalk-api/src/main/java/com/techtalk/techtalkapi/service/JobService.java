package com.techtalk.techtalkapi.service;

import com.techtalk.techtalkapi.application.jobcreate.CreateJobRequest;
import com.techtalk.techtalkapi.application.jobcreate.CreateJobResult;
import com.techtalk.techtalkapi.data.JobRepository;
import com.techtalk.techtalkapi.data.UsersRepository;
import com.techtalk.techtalkapi.domain.assembler.JobAssembler;
import com.techtalk.techtalkapi.domain.model.Job;
import com.techtalk.techtalkapi.domain.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobService {
    private final JobRepository jobRepository;
    private final UsersRepository usersRepository;
    private final JobAssembler jobAssembler;

    public List<Job> getAllJobs() {
        log.info("GetAllJobs started");
        try {
            return jobRepository.findAll();
        } catch (Exception ex) {
            log.error("GetAllJobs failed error: {}", ex.getMessage());
            return null;
        }
    }

    public Job getJob(long id) {
        log.info("GetJob started with id: {}", id);
        try {
            return jobRepository.findById(id).orElse(null);
        } catch (Exception ex) {
            log.error("GetJob failed with id: {} error: {}", id, ex.getMessage());
            return null;
        }
    }


    public CreateJobResult create(CreateJobRequest request) {
        log.info("CreateJob started with username: {}", request.getUsername());
        try {
            User user = usersRepository.findByUsername(request.getUsername()).orElse(null);
            if (Objects.isNull(user)) {
                return new CreateJobResult(false, "Kullanıcı bulunamadı.");
            }
            if (user.getLevel() < 3) {
                return new CreateJobResult(false, "İş ilanı verebilmek için en az Senior rütbeniz olması gerekli.");
            }

            Job job = jobAssembler.applyJobWithCreateRequest(request);

            if (Objects.isNull(job)) {
                return new CreateJobResult(false, "İş ilanında eksik alan!");
            }

            jobRepository.save(job);

            log.info("CreateJob finished with username: {}", request.getUsername());
            return new CreateJobResult(true, "Başarılı bir şekilde iş ilanı oluşturuldu!");
        } catch (Exception ex) {
            log.error("CreateJob failed with username: {} error: {}", request.getUsername(), ex.getMessage());
            return new CreateJobResult(false, "Sistemsel hata! Lütfen daha sonra tekrar deneyiniz.");
        }
    }

    public boolean delete(long jobId) {
        log.info("DeleteJob started with id: {}", jobId);
        try {
            Job job = jobRepository.findById(jobId).orElse(null);
            if (Objects.isNull(job)) {
                log.error("DeleteJob job not found with id: {}", jobId);
                return false;
            }

            jobRepository.delete(job);

            log.info("DeleteJob finished with id: {}", jobId);
            return true;
        } catch (Exception ex){
            log.error("DeleteJob failed with id: {} error: {}", jobId, ex.getMessage());
            return false;
        }
    }
}
