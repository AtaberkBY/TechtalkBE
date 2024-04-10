package com.techtalk.techtalkapi.application.subjectcreate;

import com.techtalk.techtalkapi.configuration.MessageConfig;
import org.springframework.stereotype.Component;

@Component
public class SubjectCreateAssembler {

    public SubjectCreateResult applyFailureResult(String message){
        return new SubjectCreateResult(
                false,
                MessageConfig.get(message)
        );
    }
    public SubjectCreateResult applySuccessResult(){
        return new SubjectCreateResult(
                true,
                MessageConfig.get("subject.create.success")
        );
    }
}
