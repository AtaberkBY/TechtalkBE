package com.techtalk.techtalkapi.application.search;

import com.techtalk.techtalkapi.domain.model.Subject;
import com.techtalk.techtalkapi.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchResult {
    private boolean success;
    private List<Subject> subjectList;
    private List<User> userList;
}
