package com.techtalk.techtalkapi.application.subjectget;

import com.techtalk.techtalkapi.domain.model.Comment;
import com.techtalk.techtalkapi.domain.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetSubjectResult {
    private boolean success;
    private Subject subject;
    private List<Comment> comments;
}
