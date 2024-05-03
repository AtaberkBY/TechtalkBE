package com.techtalk.techtalkapi.application.subjectlike;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikeSubjectRequest {
    private Long subjectId;
    private String username;
}
