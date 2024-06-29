package com.techtalk.techtalkapi.application.subjectupdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSubjectRequest {
    private Long subjectId;
    private String message;
}
