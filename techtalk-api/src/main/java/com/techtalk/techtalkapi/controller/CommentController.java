package com.techtalk.techtalkapi.controller;

import com.techtalk.techtalkapi.application.commentcreate.CreateCommentRequest;
import com.techtalk.techtalkapi.application.commentcreate.CreateCommentResult;
import com.techtalk.techtalkapi.application.commentlike.LikeCommentRequest;
import com.techtalk.techtalkapi.application.commentupdate.UpdateCommentRequest;
import com.techtalk.techtalkapi.domain.model.Comment;
import com.techtalk.techtalkapi.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/comment")
@AllArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping("/{commentId}")
    public Comment get(@PathVariable Long commentId) {
        return commentService.get(commentId);
    }

    @PostMapping("/create/{subjectId}")
    public CreateCommentResult create(@PathVariable Long subjectId, @RequestBody CreateCommentRequest request){
        return commentService.create(subjectId, request);
    }

    @DeleteMapping("/delete/{commentId}")
    public boolean delete(@PathVariable Long commentId){
        return commentService.delete(commentId);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody UpdateCommentRequest request){
        return commentService.update(request);
    }

    @PutMapping("/like")
    public boolean like(@RequestBody LikeCommentRequest request) {
        return commentService.like(request);
    }
}
