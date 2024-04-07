package com.techtalk.techtalkapi.controller;

import com.techtalk.techtalkapi.domain.subject.Subject;
import com.techtalk.techtalkapi.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/subject")
@AllArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping()
    public List<Subject> profile() {
        return subjectService.getAllSubjects();
    }
}
