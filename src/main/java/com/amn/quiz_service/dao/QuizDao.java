package com.amn.quiz_service.dao;

import com.amn.quiz_service.Exception.QuestionNotFoundException;
import com.amn.quiz_service.entity.Quiz;
import com.amn.quiz_service.repository.QuizRepository;
import com.amn.quiz_service.utils.ApiResponse;
import com.amn.quiz_service.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class QuizDao {

    private final QuizRepository quizRepository;

    public ResponseEntity<ApiResponse> createQuiz(Quiz quiz) {
        return ResponseUtils.getCreatedResponse(quizRepository.save(quiz));
    }

    public Quiz getQuiz(Integer quizId) {
        return quizRepository.findById(quizId).orElseThrow(()->new QuestionNotFoundException());
    }
}
