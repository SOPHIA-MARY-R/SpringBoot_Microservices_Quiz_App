package com.sophia.quiz_app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sophia.quiz_app.model.Question;
import com.sophia.quiz_app.model.QuestionWrapper;
import com.sophia.quiz_app.model.Quiz;
import com.sophia.quiz_app.model.Response;
import com.sophia.quiz_app.repository.QuestionRepository;
import com.sophia.quiz_app.repository.QuizRepository;

@Service
public class QuizService {

    private QuizRepository quizRepository;
    @Autowired
    public void setQuizRepository(QuizRepository quizRepository){
        this.quizRepository = quizRepository;
    }

    private QuestionRepository questionRepository;
    @Autowired
    public void setQuestionRepository(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    public ResponseEntity<List<Question>> createQuiz(String category, int numQ, String title) {

        List<Question> questions = questionRepository.findRandomQuestionsByCategory(numQ, category);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizRepository.save(quiz);

        return new ResponseEntity<>(questions, HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizById(int id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUsers = new ArrayList<>();
        
        for(Question question : questions){
            questionsForUsers.add(new QuestionWrapper(
                question.getId(), 
                question.getQuestion(),
                question.getChoice1(),
                question.getChoice2(),
                question.getChoice3(),
                question.getChoice4()));
        }

        return new ResponseEntity<>(questionsForUsers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> submitQuizAndGetScore(int id, List<Response> responses) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        List<Question> questions = quiz.get().getQuestions();

        int score = 0;
        for(Response response : responses){
            String providedAnswer = response.getResponse();
            String expectedAnswer = getExpectedAnswerForQuestion(response.getId(), questions);

            if(providedAnswer.equals(expectedAnswer)){
                score++;
            }
        }

        return new ResponseEntity<Integer>(score, HttpStatus.ACCEPTED);
    }

    private String getExpectedAnswerForQuestion(int id, List<Question> questions){
        for(Question question : questions){
            if(question.getId() == id){
                return question.getAnswer();
            }
        }
        return null;//question does not exist which is not possible as in the UI, we can fill choices for questions that exists
    }
}
