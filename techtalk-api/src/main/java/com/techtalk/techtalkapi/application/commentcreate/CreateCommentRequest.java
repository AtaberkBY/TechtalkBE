package com.techtalk.techtalkapi.application.commentcreate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCommentRequest {
    private Long userId; //TODO Burada jwtToken alınıp backend tarafında token ile user bulunabilir
    private String message;
}
