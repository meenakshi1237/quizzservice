package com.amn.quiz_service.controller;

import com.amn.quiz_service.entity.dto.QUizDto;
import com.amn.quiz_service.entity.dto.Result;
import com.amn.quiz_service.service.QuizService;
import com.amn.quiz_service.utils.ApiResponse;
import com.amn.quiz_service.utils.ResponseUtils;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("quiz")
public class QuizCantroller {
    private final QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<ApiResponse> createQuiz(@RequestBody QUizDto quizdto){
        return quizService.createQuiz(quizdto.getCatrgory(),quizdto.getNumberOfQuestions(),quizdto.getTitle());
    }

    @GetMapping("get/{quizId}")
    public ResponseEntity<ApiResponse> getQuiz(@PathVariable Integer quizId){
        return quizService.getQuiz(quizId);
    }
    @PostMapping("submit/{quizId}")
    public ResponseEntity<ApiResponse> submitQuiz(@PathVariable Integer quizId,@RequestBody List<Result>  answers){
        return quizService.caliclateResult(quizId,answers);
    }


}
