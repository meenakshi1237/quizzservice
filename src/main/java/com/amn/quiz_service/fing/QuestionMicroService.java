package com.amn.quiz_service.fing;

import com.amn.quiz_service.entity.dto.Result;
import com.amn.quiz_service.utils.ApiResponse;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("question-service")
public interface QuestionMicroService {
//here qquestions in mapping is @RequestMapping in question service so iamn giving
//or else you can give in @FeignClient only
    @GetMapping("questions/generate")
    public ResponseEntity<ApiResponse> getQuestionsForQuiz(@RequestParam String category, @RequestParam Integer noQ);


    @PostMapping("questions/getQuestions")
    public ResponseEntity<ApiResponse> getQuestionsFromIds(@RequestBody List<Integer> questionIds);

    @PostMapping("questions/getScore")
    public ResponseEntity<ApiResponse> caliclateResults(@RequestBody List<Result> answers);
}
