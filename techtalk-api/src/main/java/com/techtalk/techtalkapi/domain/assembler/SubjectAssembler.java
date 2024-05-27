package com.techtalk.techtalkapi.domain.assembler;

import com.techtalk.techtalkapi.application.subjectcreate.SubjectCreateRequest;
import com.techtalk.techtalkapi.domain.model.Subject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SubjectAssembler {

    public Subject applySubjectWithCreateRequest(SubjectCreateRequest request, byte[] userProfilePhotoUrl){
        Subject subject = new Subject();
        subject.setUsername(request.getUsername());
        subject.setMessage(request.getMessage());
        subject.setTopic(request.getTopic());
        subject.setTag(request.getTag());
        subject.setLikeCount(0);
        subject.setDislikeCount(0);
        subject.setCreatedDate(LocalDateTime.now());
        subject.setActive(true);
        subject.setCommentCount(0);
        subject.setUserProfilePhoto(userProfilePhotoUrl);

        return subject;
    }
}
