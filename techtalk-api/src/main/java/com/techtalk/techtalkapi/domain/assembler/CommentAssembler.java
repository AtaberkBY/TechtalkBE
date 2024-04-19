package com.techtalk.techtalkapi.domain.assembler;

import com.techtalk.techtalkapi.application.commentcreate.CreateCommentRequest;
import com.techtalk.techtalkapi.domain.model.Comment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommentAssembler {

    public Comment applyCommentWithCreateRequest(Long subjectId,CreateCommentRequest request){
        Comment comment = new Comment();
        comment.setSubjectId(subjectId);
        comment.setUsername(request.getUsername());
        comment.setMessage(request.getMessage());
        comment.setLikeCount(0);
        comment.setDislikeCount(0);
        comment.setCreatedDate(LocalDateTime.now());

        return comment;
    }
}
