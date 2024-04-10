package com.techtalk.techtalkapi.service;

import com.techtalk.techtalkapi.application.subjectcreate.SubjectCreateAssembler;
import com.techtalk.techtalkapi.application.subjectcreate.SubjectCreateRequest;
import com.techtalk.techtalkapi.application.subjectcreate.SubjectCreateResult;
import com.techtalk.techtalkapi.data.SubjectRepository;
import com.techtalk.techtalkapi.domain.subject.Subject;
import com.techtalk.techtalkapi.domain.subject.SubjectAssembler;
import com.techtalk.techtalkapi.validate.SubjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final SubjectAssembler subjectAssembler;
    private final SubjectCreateAssembler subjectCreateAssembler;
    private final SubjectValidator subjectValidator;

    public List<Subject> getAllSubjects() {
        log.info("Get All Subject started");
        try {
            return subjectRepository.getAllByIsActive(true);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public SubjectCreateResult subjectCreate(SubjectCreateRequest request){
        log.info("Subject creation started with subject name: {}",request.getTopic());
        try {
            String validateMessage = subjectValidator.validateSubjectCreate(request);
            if (Objects.nonNull(validateMessage)){
                return subjectCreateAssembler.applyFailureResult(validateMessage);
            }

            Subject subject = subjectAssembler.applySubjectWithCreateRequest(request);
            subjectRepository.save(subject);

            return subjectCreateAssembler.applySuccessResult();
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}
