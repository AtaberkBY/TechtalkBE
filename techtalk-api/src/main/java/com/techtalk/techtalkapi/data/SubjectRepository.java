package com.techtalk.techtalkapi.data;

import com.techtalk.techtalkapi.domain.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> getAllByIsActive(boolean status);
}
