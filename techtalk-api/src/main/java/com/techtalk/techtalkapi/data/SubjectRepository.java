package com.techtalk.techtalkapi.data;

import com.techtalk.techtalkapi.domain.subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> getAllByActive(boolean status);
}
