package com.techtalk.techtalkapi.service;

import com.techtalk.techtalkapi.application.commentcreate.CreateCommentRequest;
import com.techtalk.techtalkapi.application.commentcreate.CreateCommentResult;
import com.techtalk.techtalkapi.data.CommentRepository;
import com.techtalk.techtalkapi.domain.assembler.CommentAssembler;
import com.techtalk.techtalkapi.domain.model.Comment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentAssembler commentAssembler;

    public CreateCommentResult create(Long subjectId, CreateCommentRequest request) {
        log.info("Create comment with subject id {}, userId: {}", subjectId, request.getUserId());
        try {
            Comment comment = commentAssembler.applyCommentWithCreateRequest(subjectId, request);
            commentRepository.save(comment);

            log.info("Created comment with subject id {}, userId: {}", subjectId, request.getUserId());
            return new CreateCommentResult(true, "Yorum Oluşturuldu.");
        } catch (Exception ex) {
            log.error("Create comment error with subjectId: {}, error: {}", subjectId, ex.getMessage());
            return new CreateCommentResult(false, ex.getMessage());
        }
    }
}
