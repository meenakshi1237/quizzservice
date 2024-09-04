package com.amn.quiz_service.repository;

import com.amn.quiz_service.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Integer> {

}
