package com.techtalk.techtalkapi.application.jobupdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateJobRequest {
    private Long id;
    private String title;
    private String description;
    private String contact;
    private List<String> requirements;
}
