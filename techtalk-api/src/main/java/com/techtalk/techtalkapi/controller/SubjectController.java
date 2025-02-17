package com.techtalk.techtalkapi.controller;

import com.techtalk.techtalkapi.application.subjectcreate.SubjectCreateRequest;
import com.techtalk.techtalkapi.application.subjectcreate.SubjectCreateResult;
import com.techtalk.techtalkapi.application.subjectget.GetSubjectResult;
import com.techtalk.techtalkapi.application.subjectlike.LikeSubjectRequest;
import com.techtalk.techtalkapi.application.subjectupdate.UpdateSubjectRequest;
import com.techtalk.techtalkapi.domain.model.Subject;
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
    public @ResponseBody SubjectCreateResult subjectCreate(@RequestBody SubjectCreateRequest subjectCreateRequest) {
        return subjectService.subjectCreate(subjectCreateRequest);
    }

    @GetMapping("/{subjectId}")
    public GetSubjectResult subjects(@PathVariable Long subjectId) {
        return subjectService.getSubject(subjectId);
    }

    @DeleteMapping("/delete/{subjectId}")
    public boolean subjectDelete(@PathVariable Long subjectId) {
        return subjectService.deleteSubject(subjectId);
    }

    @GetMapping("/popular")
    public List<Subject> popularSubjects() {
        return subjectService.getPopularSubjects();
    }

    @PutMapping("/update")
    public boolean subjectUpdate(@RequestBody UpdateSubjectRequest request) {
        return subjectService.updateSubject(request);
    }

    @PutMapping("/like")
    public boolean subjectLike(@RequestBody LikeSubjectRequest likeSubjectRequest) {
        return subjectService.likeSubject(likeSubjectRequest);
    }

    @GetMapping("/trendtag")
    public List<String> trendTags() {
        return subjectService.getTrendTags();
    }
}
