package com.techtalk.techtalkapi.validate;

import com.techtalk.techtalkapi.application.subjectcreate.SubjectCreateRequest;
import org.springframework.stereotype.Component;

@Component
public class SubjectValidator {

    public String validateSubjectCreate(SubjectCreateRequest request){
        if(request.getTopic().length() > 255){
            return "subject.create.topic-length.invalid";
        }
        if(request.getMessage().length() > 500){
            return "subject.create.message-length.invalid";
        }
        if(request.getTag().length() > 255){
            return "subject.create.tag-length.invalid";
        }

        return null;
    }
}
