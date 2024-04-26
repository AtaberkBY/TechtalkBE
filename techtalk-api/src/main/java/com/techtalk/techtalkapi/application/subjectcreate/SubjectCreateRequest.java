package com.techtalk.techtalkapi.application.subjectcreate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectCreateRequest {
    private String username;
    private String topic;
    private String message;
    private String tag;
}
