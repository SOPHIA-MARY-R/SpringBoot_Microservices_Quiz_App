package com.sophia.quiz_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sophia.quiz_app.model.Question;
import com.sophia.quiz_app.model.QuestionWrapper;
import com.sophia.quiz_app.service.QuizService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/quiz")
public class QuizController {

    private QuizService service;
    @Autowired
    public void setQuizServcie(QuizService service){
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<List<Question>> createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return service.createQuiz(category, numQ, title);
    }
    
    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizById(@PathVariable int id) {
        return service.getQuizById(id);
    }
    
}
