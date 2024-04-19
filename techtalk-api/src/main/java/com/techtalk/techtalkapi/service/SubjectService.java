package com.techtalk.techtalkapi.service;

import com.techtalk.techtalkapi.application.subjectcreate.SubjectCreateAssembler;
import com.techtalk.techtalkapi.application.subjectcreate.SubjectCreateRequest;
import com.techtalk.techtalkapi.application.subjectcreate.SubjectCreateResult;
import com.techtalk.techtalkapi.application.subjectget.GetSubjectResult;
import com.techtalk.techtalkapi.data.CommentRepository;
import com.techtalk.techtalkapi.data.SubjectRepository;
import com.techtalk.techtalkapi.domain.model.Comment;
import com.techtalk.techtalkapi.domain.model.Subject;
import com.techtalk.techtalkapi.domain.assembler.SubjectAssembler;
import com.techtalk.techtalkapi.validate.SubjectValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final SubjectAssembler subjectAssembler;
    private final SubjectCreateAssembler subjectCreateAssembler;
    private final SubjectValidator subjectValidator;
    private final CommentRepository commentRepository;

    public List<Subject> getAllSubjects() {
        log.info("Get All Subject started");
        try {
            return subjectRepository.getAllByIsActive(true);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public SubjectCreateResult subjectCreate(SubjectCreateRequest request) {
        log.info("Subject creation started with subject name: {}", request.getTopic());
        try {
            String validateMessage = subjectValidator.validateSubjectCreate(request);
            if (Objects.nonNull(validateMessage)) {
                return subjectCreateAssembler.applyFailureResult(validateMessage);
            }

            Subject subject = subjectAssembler.applySubjectWithCreateRequest(request);
            subjectRepository.save(subject);

            return subjectCreateAssembler.applySuccessResult();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public GetSubjectResult getSubject(Long subjectId) {
        log.info("Get Subject started with subjectId: {}", subjectId);
        try {
            Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);
            if (subjectOptional.isEmpty()) {
                return new GetSubjectResult(false, null, null);
            }
            List<Comment> comments = commentRepository.findAllBySubjectId(subjectId);

            log.info("Get Subject finished with subjectId: {}", subjectId);
            return new GetSubjectResult(true, subjectOptional.get(), comments);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public boolean deleteSubject(Long subjectId) {
        log.info("Delete Subject started with subjectId: {}", subjectId);
        try {
            Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);
            if (subjectOptional.isEmpty()) {
                return false;
            }
            Subject subject = subjectOptional.get();
            subject.setActive(false);
            subjectRepository.save(subject);

            log.info("Delete Subject finished with subjectId: {}", subjectId);
            return true;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Subject> getPopularSubjects() {
        log.info("Get Popular Subjects started");
        try {
            List<Subject> todaySubjects = subjectRepository.getAllByIsActive(true)
                    .stream()
                    .filter(subject -> subject.getCreated_date().toLocalDate().isEqual(LocalDate.now()))
                    .toList();

            Map<Subject, Double> subjectScores = todaySubjects.stream()
                    .collect(Collectors.toMap(
                            subject -> subject,
                            this::calculateScore
                    ));

            List<Subject> popularSubjects = subjectScores.entrySet()
                    .stream()
                    .sorted((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue()))
                    .limit(3)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            log.info("Get Popular Subjects finished with popular subject count: {}", popularSubjects.size());
            return popularSubjects;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private double calculateScore(Subject subject) {
        double score = 0.1 * subject.getLike_count();
        score += subject.getDislike_count() * -0.1;
        return score;
    }
}
