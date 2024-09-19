package com.learntoearn.learntoearn.repository;

import com.learntoearn.learntoearn.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface extends JpaRepository and provides CRUD operations for the `Question` entity.
 */
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
