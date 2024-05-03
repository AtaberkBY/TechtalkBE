package com.techtalk.techtalkapi.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subject_like")
public class SubjectLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long subjectId;
    private String username;

    public SubjectLike(Long commentId, String username) {
        this.subjectId = commentId;
        this.username = username;
    }
}
