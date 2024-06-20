package com.techtalk.techtalkapi.application.jobcreate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobRequest {
    private String username;
    private String title;
    private String description;
    private String contact;
    private List<String> requirements;
}
