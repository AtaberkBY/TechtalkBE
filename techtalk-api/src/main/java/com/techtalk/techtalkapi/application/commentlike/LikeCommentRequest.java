package com.techtalk.techtalkapi.application.commentlike;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LikeCommentRequest {
    private Long commentId;
    private String username;
}
