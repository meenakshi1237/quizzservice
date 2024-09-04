package com.amn.quiz_service.Exception;

import com.amn.quiz_service.utils.ApiResponse;
import com.amn.quiz_service.utils.ResponseUtils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<ApiResponse> questionNotFoundException(QuestionNotFoundException questionNotFoundException){
        return ResponseUtils.getBadRequestResponse(questionNotFoundException.getMessage());
    }

   @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<ApiResponse> quizNotFouundException(QuestionNotFoundException questionNotFoundException){
        return ResponseUtils.getBadRequestResponse(questionNotFoundException.getMessage());
    }

}
