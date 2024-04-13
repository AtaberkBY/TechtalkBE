package com.techtalk.techtalkapi.application.commentcreate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentResult {
    private boolean success;
    private String resultMessage;
}
