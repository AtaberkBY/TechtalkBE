package com.techtalk.techtalkapi.application.jobcreate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobResult {
    private boolean success;
    private String resultMessage;
}
