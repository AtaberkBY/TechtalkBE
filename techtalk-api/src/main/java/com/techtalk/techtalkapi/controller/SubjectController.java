package com.techtalk.techtalkapi.controller;

import com.techtalk.techtalkapi.application.subjectcreate.SubjectCreateRequest;
import com.techtalk.techtalkapi.application.subjectcreate.SubjectCreateResult;
import com.techtalk.techtalkapi.application.subjectget.GetSubjectResult;
import com.techtalk.techtalkapi.domain.subject.Subject;
import com.techtalk.techtalkapi.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/subject")
@AllArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping()
    public List<Subject> subjects() {
        return subjectService.getAllSubjects();
    }

    @PostMapping("/create")
    public @ResponseBody SubjectCreateResult subjectCreate(@RequestBody SubjectCreateRequest subjectCreateRequest){
        return subjectService.subjectCreate(subjectCreateRequest);
    }

    @GetMapping("/{subjectId}")
    public GetSubjectResult subjects(@PathVariable Long subjectId) {
        return subjectService.getSubject(subjectId);
    }

    @PostMapping("/delete/{subjectId}")
    public boolean subjectDelete(@PathVariable Long subjectId){
        return subjectService.deleteSubject(subjectId);
    }
}
