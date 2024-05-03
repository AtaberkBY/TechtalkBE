package com.techtalk.techtalkapi.data;

import com.techtalk.techtalkapi.domain.model.SubjectLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectLikeRepository extends JpaRepository<SubjectLike, Long> {
    boolean existsByUsernameAndSubjectId(String username, Long subjectId);
    SubjectLike findByUsernameAndSubjectId(String username, Long subjectId);
}
