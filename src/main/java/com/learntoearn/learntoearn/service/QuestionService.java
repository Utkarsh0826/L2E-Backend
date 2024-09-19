package com.learntoearn.learntoearn.service;

import com.learntoearn.learntoearn.model.Question;
import com.learntoearn.learntoearn.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question addQuestion(Question question) {
        validateQuestionType(question);
        return questionRepository.save(question);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    public Question updateQuestion(Long id, Question updatedQuestion) {
        return questionRepository.findById(id)
                .map(question -> {
                    question.setType(updatedQuestion.getType());
                    question.setExam(updatedQuestion.getExam());
                    question.setExamNo(updatedQuestion.getExamNo());
                    question.setEqNo(updatedQuestion.getEqNo());
                    question.setSubject(updatedQuestion.getSubject());
                    question.setQuestion(updatedQuestion.getQuestion());
                    question.setOptionA(updatedQuestion.getOptionA());
                    question.setOptionB(updatedQuestion.getOptionB());
                    question.setOptionC(updatedQuestion.getOptionC());
                    question.setOptionD(updatedQuestion.getOptionD());
                    question.setAnswer(updatedQuestion.getAnswer());
                    
                    validateQuestionType(question);
                    
                    return questionRepository.save(question);
                }).orElseThrow(() -> new RuntimeException("Question not found"));
    }

    private void validateQuestionType(Question question) {
        // If type is "Numerical", set options to null
        if ("Numerical".equalsIgnoreCase(question.getType())) {
            question.setOptionA(null);
            question.setOptionB(null);
            question.setOptionC(null);
            question.setOptionD(null);
        }
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
