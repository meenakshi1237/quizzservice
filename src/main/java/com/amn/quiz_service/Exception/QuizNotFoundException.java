package com.amn.quiz_service.Exception;

public class QuizNotFoundException extends RuntimeException{

    public String getMessage(){
        return "Quiz Not Found";
    }
}
