package com.techtalk.techtalkapi.service;

import com.techtalk.techtalkapi.application.commentcreate.CreateCommentRequest;
import com.techtalk.techtalkapi.application.commentcreate.CreateCommentResult;
import com.techtalk.techtalkapi.application.commentlike.LikeCommentRequest;
import com.techtalk.techtalkapi.data.CommentLikeRepository;
import com.techtalk.techtalkapi.data.CommentRepository;
import com.techtalk.techtalkapi.data.SubjectRepository;
import com.techtalk.techtalkapi.domain.assembler.CommentAssembler;
import com.techtalk.techtalkapi.domain.model.Comment;
import com.techtalk.techtalkapi.domain.model.CommentLike;
import com.techtalk.techtalkapi.domain.model.Subject;
import com.techtalk.techtalkapi.utility.PointUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentAssembler commentAssembler;
    private final CommentLikeRepository commentLikeRepository;
    private final SubjectRepository subjectRepository;
    private final PointUtility pointUtility;

    public Comment get(Long commentId) {
        log.info("Get comment by commentId: {}", commentId);
        try {
            Comment comment = commentRepository.findById(commentId).orElse(null);
            if (Objects.isNull(comment)) {
                log.warn("Comment not found with commentId: {}", commentId);
                return null;
            }

            return comment;
        } catch (Exception ex) {
            log.error("Get comment error: {}", ex.getMessage());
            throw new RuntimeException(ex);
        }
    }

    public CreateCommentResult create(Long subjectId, CreateCommentRequest request) {
        log.info("Create comment with subject id {}", subjectId);
        try {
            Comment comment = commentAssembler.applyCommentWithCreateRequest(subjectId, request);
            commentRepository.save(comment);
            pointUtility.givePointToUser(comment.getUsername(), 3);

            Subject subject = subjectRepository.getReferenceById(subjectId);
            subject.setCommentCount(subject.getCommentCount() + 1);
            subjectRepository.save(subject);

            log.info("Created comment with subject id {}", subjectId);
            return new CreateCommentResult(true, "Yorum Oluşturuldu.");
        } catch (Exception ex) {
            log.error("Create comment error with subjectId: {}, error: {}", subjectId, ex.getMessage());
            return new CreateCommentResult(false, ex.getMessage());
        }
    }

    public boolean delete(Long commentId) {
        log.info("Delete comment with commentId {}", commentId);
        try {
            Comment comment = commentRepository.findById(commentId).orElse(null);
            if (comment == null) {
                log.warn("Comment with commentId {} not found.", commentId);
                return false;
            }
            commentRepository.delete(comment);

            log.info("Deleted comment with commentId {}", commentId);
            return true;
        } catch (Exception ex) {
            log.error("Delete comment error with commentId {}, error: {}", commentId, ex.getMessage());
            return false;
        }
    }

    public boolean like(LikeCommentRequest request) {
        log.info("Like comment with commentId {}, username: {}", request.getCommentId(), request.getUsername());
        try {
            Comment comment = commentRepository.findById(request.getCommentId()).orElse(null);
            if (comment == null) {
                return false;
            }

            if (commentLikeRepository.existsByUsernameAndCommentId(request.getUsername(), request.getCommentId())) {
                comment.setLikeCount(comment.getLikeCount() - 1);
                commentRepository.save(comment);
                commentLikeRepository.delete(commentLikeRepository.findByUsernameAndCommentId(request.getUsername(), request.getCommentId()));

                log.info("Unliked comment with commentId {} and username {}", request.getCommentId(), request.getUsername());
                return true;
            }

            comment.setLikeCount(comment.getLikeCount() + 1);
            commentRepository.save(comment);
            commentLikeRepository.save(new CommentLike(request.getCommentId(), request.getUsername()));

            pointUtility.givePointToUser(comment.getUsername(), 1);

            log.info("Liked comment with commentId {} and username {}", request.getCommentId(), request.getUsername());
            return true;
        } catch (Exception ex) {
            log.error("Like comment error with commentId {}, username: {}, error: {}", request.getCommentId(), request.getUsername(), ex.getMessage());
            return false;
        }
    }
}
