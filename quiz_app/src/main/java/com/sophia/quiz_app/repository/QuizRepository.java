package com.sophia.quiz_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sophia.quiz_app.model.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer>{

}
