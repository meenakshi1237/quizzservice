package com.amn.quiz_service.service;

import com.amn.quiz_service.dao.QuizDao;
import com.amn.quiz_service.entity.Quiz;
import com.amn.quiz_service.entity.dto.QuestionWrapper;
import com.amn.quiz_service.entity.dto.Result;
import com.amn.quiz_service.fing.QuestionMicroService;
import com.amn.quiz_service.utils.ApiResponse;
import com.amn.quiz_service.utils.ResponseUtils;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizDao quizDao;

//    private final QuestionDao questionDao;

    private final ModelMapper modelMapper;

    private final QuestionMicroService questionMicroService;

    private final WebClient.Builder webclient;

    private static final String QUESTION_SERVICE="QUESTION-SERVICE";

    @RateLimiter(name = "QUESTION-SERVICE",fallbackMethod = "rateLimiterFallBackMethod")
    public ResponseEntity<ApiResponse> createQuiz(String category, int numQ, String title) {
        //initially we are using questionDao to create question
//        List<Question> questions=questionDao.getLimitedQuestionsByCategory(category,numQ);
        //for fign client
//        List<Integer> questionIds= (List<Integer>) questionMicroService.getQuestionsForQuiz(category,numQ).getBody().getResponse();

        //for web client
        List<Integer> questionIds = (List<Integer>) webclient.build().get()
                .uri(uri->uri.scheme("http")
                        .host("localhost")
                        .port(8081)
                        .path("/questions/generate")
                        .queryParam("category",category)
                        .queryParam("noQ",numQ)
                        .build()
                )
                .retrieve().toEntity(ApiResponse.class).block().getBody().getResponse();


        Quiz quiz=Quiz.builder()
                .title(title)
                .questions(questionIds)
                .build();
        return quizDao.createQuiz(quiz);

        //But now we have to send a request to the api present in the question service to create questions
    }
    @CircuitBreaker(name = "QUESTION-SERVICE",fallbackMethod = "questionServiceFallBackMethod")
    public ResponseEntity<ApiResponse> getQuiz(Integer quizId) {
//        System.err.println("trying to connect" + c++ +new Date());
        Quiz quiz=quizDao.getQuiz(quizId);
        List<Integer> questions=quiz.getQuestions();
        List<QuestionWrapper> userQuestions= (List<QuestionWrapper>) questionMicroService.getQuestionsFromIds(questions).getBody().getResponse();
//        for(Question qw:questions){
//            QuestionWrapper  questionWrapper=modelMapper.map(qw,QuestionWrapper.class);
//            userQuestions.add(questionWrapper);
//        }
        return ResponseUtils.getOkResponse(userQuestions);
    }
    int c=0;
    @Retry(name = "QUESTION-SERVICE",fallbackMethod = "questionServiceFallBackMethod")
    public ResponseEntity<ApiResponse> caliclateResult(Integer quizId, List<Result> answers) {
        System.err.println("trying to connect" + c++ +new Date());
//        Quiz quiz=quizDao.getQuiz(quizId);
//        List<Question> questions=quiz.getQuestions();
//        int score=0;
        int score= (int) questionMicroService.caliclateResults(answers).getBody().getResponse();
//        for(Question question:questions){
//            for(Result result:answers){
//                if(question.getId()==result.getId()&&question.getCorrectAnswer().equalsIgnoreCase(result.getAnswer())){
//                    score++;
//                }
//            }
//        }
        return ResponseUtils.getOkResponse(score);

    }

    public ResponseEntity<ApiResponse> questionServiceFallBackMethod(Exception e){
        return ResponseUtils.getQuestionServiceDown("QuestionService Is Down");
    }

    public ResponseEntity<ApiResponse> rateLimiterFallBackMethod(Exception e){
        return ResponseUtils.getBadRequestResponse("Weight For some time and");
    }


}
