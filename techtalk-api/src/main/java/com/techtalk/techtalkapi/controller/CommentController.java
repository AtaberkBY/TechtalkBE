package com.techtalk.techtalkapi.controller;

import com.techtalk.techtalkapi.application.commentcreate.CreateCommentRequest;
import com.techtalk.techtalkapi.application.commentcreate.CreateCommentResult;
import com.techtalk.techtalkapi.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/comment")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/create/{subjectId}")
    public CreateCommentResult create(@PathVariable Long subjectId, @RequestBody CreateCommentRequest request){
        return commentService.create(subjectId, request);
    }

    @DeleteMapping("/delete/{commentId}")
    public boolean delete(@PathVariable Long commentId){
        return commentService.delete(commentId);
    }
}
