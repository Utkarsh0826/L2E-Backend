package com.learntoearn.learntoearn.controller;

import com.learntoearn.learntoearn.model.Question;
import com.learntoearn.learntoearn.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * This controller exposes the API endpoints for managing questions.
 * It includes endpoints for adding, retrieving, updating, and deleting questions.
 */
@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    /**
     * Endpoint to add a new question.
     * @param question The question object to add.
     * @return The saved question object.
     */
    @PostMapping
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        Question savedQuestion = questionService.addQuestion(question);
        return ResponseEntity.ok(savedQuestion);
    }

    /**
     * Endpoint to get all questions.
     * @return A list of all questions.
     */
    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return ResponseEntity.ok(questions);
    }

    /**
     * Endpoint to get a question by its ID.
     * @param id The ID of the question.
     * @return The question if found, otherwise 404.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable Long id) {
        Optional<Question> question = questionService.getQuestionById(id);
        return question.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint to update a question by its ID.
     * @param id The ID of the question to update.
     * @param updatedQuestion The updated question data.
     * @return The updated question if found, otherwise 404.
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable Long id, @RequestBody Question updatedQuestion) {
        try {
            Question question = questionService.updateQuestion(id, updatedQuestion);
            return ResponseEntity.ok(question);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint to delete a question by its ID.
     * @param id The ID of the question to delete.
     * @return A 204 No Content response if successful, otherwise 404.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long id) {
        try {
            questionService.deleteQuestion(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
