package com.techtalk.techtalkapi.data;

import com.techtalk.techtalkapi.domain.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> getAllByIsActive(boolean status);

    @Query("SELECT s.tag, COUNT(s), SUM(s.likeCount), COUNT(DISTINCT s.topic) FROM Subject s GROUP BY s.tag")
    List<Object[]> countTagsAndLikesAndTopics();
    @Query("SELECT s FROM Subject s WHERE (LOWER(s.tag) LIKE %:key% OR LOWER(s.topic) LIKE %:key%) AND s.isActive = true")
    List<Subject> searchActiveSubjectsByKey(@Param("key") String key);
    @Query("SELECT s FROM Subject s WHERE LOWER(s.tag) LIKE %:key% AND s.isActive = true")
    List<Subject> searchTagByKey(@Param("key") String key);
}
