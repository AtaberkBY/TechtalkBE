package com.techtalk.techtalkapi.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String title;
    private String description;
    private String contactEmail;
    @ElementCollection
    @CollectionTable(name = "job_requirements", joinColumns = @JoinColumn(name = "job_id"))
    @Column(name = "requirement")
    private List<String> requirements;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
