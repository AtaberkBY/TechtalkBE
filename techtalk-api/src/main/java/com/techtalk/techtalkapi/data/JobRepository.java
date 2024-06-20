package com.techtalk.techtalkapi.data;

import com.techtalk.techtalkapi.domain.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}
