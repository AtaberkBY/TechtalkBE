package com.techtalk.techtalkapi.service;

import com.techtalk.techtalkapi.data.SubjectRepository;
import com.techtalk.techtalkapi.domain.subject.Subject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public List<Subject> getAllSubjects() {
        log.info("Get All Subject started");
        try {
            return subjectRepository.getAllByActive(true);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
